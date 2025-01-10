package com.xjtu.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁注解
 *
 * @author xujie
 * @since 2025/01/10 15:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {

    /**
     * 锁的场景
     *
     * @return
     */
    public String scene();

    /**
     * 加锁的key，优先取key(),如果没有，则取keyExpression()
     *
     * @return
     */
    public String key();

    /**
     * sPel表达式，
     * <pre>
     *     #id
     *     #insertResult.id
     * </pre>
     *
     * @return
     */
    public String keyExpression();

    /**
     * 超时时间，ms
     * 默认情况下不设置超时时间，会自动续期
     *
     * @return
     */
    public int expireTime();

    /**
     * 加锁等待时间,ms
     * 默认情况下不设置等待时常，不做等待
     *
     * @return
     */
    public int waitTime();
}
