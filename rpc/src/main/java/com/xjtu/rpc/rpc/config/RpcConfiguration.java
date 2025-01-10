package com.xjtu.rpc.rpc.config;

import com.xjtu.rpc.rpc.facade.FacadeAspect;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujie
 * @since 2025/01/10 14:52
 */
@Configuration
@DubboService
public class RpcConfiguration
{
    @Bean
    public FacadeAspect facadeAspect(){
        return new FacadeAspect();
    }
}
