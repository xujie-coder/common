package com.xjtu.base.exception;

/**
 * 远程调用异常
 *
 * @author xujie
 * @since 2025/01/07 16:09
 */
public class RemoteCallException extends SystemException{
    public RemoteCallException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RemoteCallException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public RemoteCallException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public RemoteCallException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public RemoteCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
