package com.xjtu.base.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.ImmutableSet;
import com.xjtu.base.exception.BizErrorCode;
import com.xjtu.base.exception.RemoteCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;


/**
 * 统一远程调用包装工具类
 *
 * @author xujie
 * @since 2025/01/07 21:05
 */
@Slf4j
public class RemoteCallWrapper {

    private static ImmutableSet<String> SUCCESS_CHECK_METHOD = ImmutableSet.of("isSuccess","isSucceeded","getSuccess");

    private static ImmutableSet<String> SUCCESS_CODE_METHOD = ImmutableSet.of("getResponseCode");

    private static ImmutableSet<String> SUCCESS_CODE = ImmutableSet.of("SUCCESS","DUPLICATE","DUPLICATED_REQUEST");

    public static <T,R> R call(Function<T, R> function, T request){
        return call(function,request,request.getClass().getSimpleName(),true,false);
    }

    public static <T,R> R call(Function<T, R> function, T request,String requestName){
        return call(function,request,requestName,true,false);
    }

    public static <T,R> R call(Function<T, R> function, T request,boolean checkResponse){
        return call(function,request,request.getClass().getSimpleName(),checkResponse,false);
    }

    public static <T,R> R call(Function<T, R> function, T request,String requestName,boolean checkResponse){
        return call(function,request,requestName,checkResponse,false);
    }

    public static <T,R> R call(Function<T, R> function, T request,boolean checkResponse,boolean checkResponseCode){
        return call(function,request,request.getClass().getSimpleName(),checkResponse,checkResponseCode);
    }

    public static <T, R> R call(Function<T, R> function, T request, String requestName, boolean checkResponse,
                                boolean checkResponseCode) {
        StopWatch stopWatch = new StopWatch();
        R response = null;

        try {
            stopWatch.start();
            response = function.apply(request);
            stopWatch.stop();

            if (checkResponse){
                Assert.notNull(response, BizErrorCode.REMOTE_CALL_RESPONSE_IS_NULL.name());

                if (!isResponseValid(response)) {
                    log.error("Response code Invalid on Remote call request {} , response {}",
                            JSON.toJSONString(request),
                            JSON.toJSONString(response));
                    throw new RemoteCallException(JSON.toJSONString(response),BizErrorCode.REMOTE_CALL_RESPONSE_IS_FAILED);
                }
            }

            if (checkResponseCode) {
                Assert.notNull(response,BizErrorCode.REMOTE_CALL_RESPONSE_IS_NULL.name());

                if (!isResponseCodeValid(response)) {
                    log.error("Response code Invalid on Remote call request {} , response {}",
                            JSON.toJSONString(request),
                            JSON.toJSONString(response));
                    throw new RemoteCallException(JSON.toJSONString(response),BizErrorCode.REMOTE_CALL_RESPONSE_IS_FAILED);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e ){
            log.error("Catch Exception on Remote Call：" + e.getMessage(),e);
            throw new IllegalArgumentException("Catch Exception on Remote Call：" + e.getMessage(),e);
        } catch (Throwable e){
            log.error("request Exception {}",JSON.toJSONString(request));
            log.error("Catch Exception on Remote Call：{}", e.getMessage(),e);
            throw e;
        } finally {
            if (log.isInfoEnabled()) {
                log.info("## Method={}, ## 耗时={}ms, ## [请求报文]：{}, ## [响应报文]：{}",requestName,
                        stopWatch.getTotalTimeMillis(),JSON.toJSONString(request),JSON.toJSONString(response));
            }
        }
        return response;
    }

    private static <R> boolean isResponseValid(R response) throws InvocationTargetException, IllegalAccessException {
        Method successMethod = null;
        Method[] methods = response.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (SUCCESS_CHECK_METHOD.contains(name)) {
                successMethod = method;
                break;
            }
        }
        if (successMethod == null){
            return true;
        }
        return (Boolean) successMethod.invoke(response);
    }

    private static <R> boolean isResponseCodeValid(R response) throws InvocationTargetException, IllegalAccessException {
        Method successMethod = null;
        Method[] methods = response.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (SUCCESS_CODE_METHOD.contains(name)) {
                successMethod = method;
                break;
            }
        }
        if (successMethod == null) {
            return true;
        }
        return SUCCESS_CODE.contains(successMethod.invoke(response));
    }
}
