package com.wuwei.consumer.wechat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.wuwei")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerWeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerWeChatApplication.class,args);
    }
}