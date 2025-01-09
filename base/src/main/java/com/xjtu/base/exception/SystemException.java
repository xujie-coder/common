package com.xjtu.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统异常
 *
 * @author xujie
 * @since 2025/01/07 15:44
 */
@Getter
@Setter
public class SystemException extends RuntimeException{
    private ErrorCode errorCode;

    public SystemException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SystemException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     *
     * @param message 错误信息
     * @param cause 导致当前异常发生的原因
     * @param enableSuppression 是否启用抑制机制
     * @param writableStackTrace    是否使堆栈跟踪信息可写
     * @param errorCode·指定错误类型
     */
    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
