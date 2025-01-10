package com.xjtu.cache.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujie
 * @since 2025/01/10 15:39
 */
@Configuration
@EnableMethodCache(basePackages = "com.xjtu")
public class CacheConfiguration {
}
