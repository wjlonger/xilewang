package com.wuwei.provider.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.wuwei.provider.wechat.mapper")
@EnableEurekaClient
@SpringBootApplication
public class ProviderWeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderWeChatApplication.class,args);
    }
}
