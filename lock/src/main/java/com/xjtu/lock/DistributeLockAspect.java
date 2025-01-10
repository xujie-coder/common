package com.xjtu.lock;

import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xujie
 * @since 2025/01/10 16:20
 */
@Aspect
@Component
public class DistributeLockAspect {

    @Autowired
    private RedissonClient redissonClient;
}
