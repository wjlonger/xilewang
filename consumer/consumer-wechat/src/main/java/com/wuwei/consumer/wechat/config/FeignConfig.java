package com.wuwei.consumer.wechat.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig{


    public FeignConfig(){
        System.out.println("FeignConfig");
    }

    /**
     * 使用Feign自己的注解，使用springmvc的注解就会报错
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

}
