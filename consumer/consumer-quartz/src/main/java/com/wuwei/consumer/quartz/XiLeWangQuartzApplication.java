package com.wuwei.consumer.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.wuwei.consumer.quartz")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class XiLeWangQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiLeWangQuartzApplication.class,args);
    }

}
