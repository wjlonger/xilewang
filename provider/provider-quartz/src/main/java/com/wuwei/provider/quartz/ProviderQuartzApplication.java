package com.wuwei.provider.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.wuwei.provider.quartz.mapper")
@EnableEurekaClient
@SpringBootApplication
public class ProviderQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderQuartzApplication.class,args);
    }

}
