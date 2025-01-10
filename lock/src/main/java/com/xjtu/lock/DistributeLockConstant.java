package com.xjtu.lock;

/**
 * 分布式锁常量
 *
 * @author xujie
 * @since 2025/01/10 15:51
 */
public class DistributeLockConstant {
    /**
     * 调用者没有传key
     */
    public static final String NONE_KEY = "NONE";

    /**
     * 默认使用者
     */
    public static final String DEFAULT_OWNER = "DEFAULT";

    /**
     * 超时时间
     */
    public static final int DEFAULT_EXPIRE_TIME = -1;

    /**
     * 等待时间
     */
    public static final int DEFAULT_WAIT_TIME = -1;
}
