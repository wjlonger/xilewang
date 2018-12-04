package com.wuwei.provider.wechat.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSenderConfig {

    @Bean
    public Queue xiLeWangUserSave(){
        return new Queue("xilewang_user_save");
    }
}
