package com.xjtu.lock;

/**
 * 分布式锁异常
 *
 * @author xujie
 * @since 2025/01/10 15:47
 */
public class DistributeLockException extends RuntimeException{


    public DistributeLockException() {
        super();
    }

    public DistributeLockException(String message) {
        super(message);
    }

    public DistributeLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributeLockException(Throwable cause) {
        super(cause);
    }

    public DistributeLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
