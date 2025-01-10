package com.xjtu.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author xujie
 * @since 2025/01/10 16:20
 */
@Aspect
@Component
public class DistributeLockAspect {

    @Autowired
    private RedissonClient redissonClient;

    private static Logger log = LoggerFactory.getLogger(DistributeLockAspect.class);

    @Around("@annotation(com.xjtu.lock.DistributeLock)")
    public Object process(ProceedingJoinPoint pjp) throws Exception {
        Object response = null;
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        String key = distributeLock.key();
        if (DistributeLockConstant.NONE_KEY.equals(key)) {
            if (DistributeLockConstant.NONE_KEY.equals(distributeLock.keyExpression())) {
                throw new DistributeLockException("no lock key find...");
            }
            // SPel用于解析表达式的核心类
            SpelExpressionParser parser = new SpelExpressionParser();
            // 得到解析后的表达式
            Expression expression = parser.parseExpression(distributeLock.keyExpression());

            // SPel用于存储和传递 表达式执行所需的上下文数据
            StandardEvaluationContext context = new StandardEvaluationContext();

            // 获取参数值
            Object[] args = pjp.getArgs();

            // 获取运行时参数名称
            StandardReflectionParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);

            // 将参数绑定到context中，一一对应
            if (parameterNames != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i],args[i]);
                }
            }

            // 解析表达式，获取结果
            key = String.valueOf(expression.getValue(context));
        }
        String scene = distributeLock.scene();
        String lockKey = scene + "#" + key;
        int expireTime = distributeLock.expireTime();
        int waitTime = distributeLock.waitTime();
        RLock rlock = redissonClient.getLock(lockKey);
        Boolean lockResult = false;

        if (waitTime == DistributeLockConstant.DEFAULT_EXPIRE_TIME) {
            if (expireTime == DistributeLockConstant.DEFAULT_EXPIRE_TIME) {
                log.info("lock for key:{}",lockKey);
                rlock.lock();
            }else {
                log.info("lock for key:{} expireTime:{}",lockKey,expireTime);
                rlock.lock(expireTime, TimeUnit.MILLISECONDS);
            }
            lockResult = true;
        }else {
            if (expireTime == DistributeLockConstant.DEFAULT_EXPIRE_TIME) {
                log.info("lock for key:{} waitTime:{}",lockKey,waitTime);
                lockResult = rlock.tryLock(waitTime, TimeUnit.MILLISECONDS);
            }else {
                log.info("lock for key:{} expireTime:{} waitTime:{}",lockKey,expireTime,waitTime);
                rlock.lock(expireTime, TimeUnit.MILLISECONDS);
                lockResult = rlock.tryLock(waitTime,expireTime,TimeUnit.MILLISECONDS);
            }
        }
        if (!lockResult) {
            log.warn("lock failed for key:{} expireTime:{}",lockKey,expireTime);
            throw new DistributeLockException("acquire lock failed... key:" + lockKey);
        }

        try {
            response = pjp.proceed();
        } catch (Throwable e) {
            throw new Exception(e);
        } finally {
            rlock.unlock();
            log.info("unlock for key:{} expire:{}",lockKey,expireTime);
        }
        return response;
    }
}
