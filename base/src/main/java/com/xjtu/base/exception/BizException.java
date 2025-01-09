package com.xjtu.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author xujie
 * @since 2025/01/07 16:34
 */
@Getter
@Setter
public class BizException extends RuntimeException{
    private ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BizException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BizException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
