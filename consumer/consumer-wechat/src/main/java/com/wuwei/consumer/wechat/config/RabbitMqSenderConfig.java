package com.wuwei.consumer.wechat.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSenderConfig {

    @Bean
    public Queue xiLeWangHistoryInsert(){
        return new Queue("xilewang_history_insert");
    }

}
