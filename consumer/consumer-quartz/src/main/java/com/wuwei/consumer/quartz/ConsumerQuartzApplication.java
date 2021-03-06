package com.wuwei.consumer.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.wuwei.consumer.quartz")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class ConsumerQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerQuartzApplication.class,args);
    }

}
