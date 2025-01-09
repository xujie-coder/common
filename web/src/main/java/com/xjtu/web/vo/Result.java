package com.xjtu.web.vo;

import lombok.Getter;
import lombok.Setter;
import com.xjtu.base.response.ResponseCode;
import com.xjtu.base.response.SingleResponse;

/**
 * @author xujie
 * @since 2025/01/09 17:03
 */
@Getter
@Setter
public class Result<T> {

    private String code;

    private Boolean success;

    private String message;

    private T data;

    public Result() {
    }

    public Result(String code, Boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(SingleResponse<T> singleResponse){
        this.code = singleResponse.getResponseCode();
        this.success = singleResponse.getSuccess();
        this.message = singleResponse.getResponseMessage();
        this.data = singleResponse.getData();
    }

    public static <T> Result <T> success(T data){
        return new Result<>(ResponseCode.SUCCESS.name(),true,ResponseCode.SUCCESS.name(),data);
    }

    public static <T> Result <T> error(String errorCode,String errorMsg){
        return new Result<>(errorCode,false,errorMsg,null);
    }
}
