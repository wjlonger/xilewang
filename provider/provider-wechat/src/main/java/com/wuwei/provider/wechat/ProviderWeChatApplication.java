package com.wuwei.provider.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProviderWeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderWeChatApplication.class,args);
    }
}
