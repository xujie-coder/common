package com.xjtu.config;

import com.xjtu.lock.DistributeLockAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujie
 * @since 2025/01/10 16:39
 */
@Configuration
public class DistributeLockConfiguration {
    @Bean
    public DistributeLockAspect distributeLockAspect(){
        return new DistributeLockAspect();
    }
}
