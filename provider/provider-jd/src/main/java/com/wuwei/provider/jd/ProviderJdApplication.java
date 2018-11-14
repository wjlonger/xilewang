package com.wuwei.provider.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProviderJdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderJdApplication.class,args);
    }

}
