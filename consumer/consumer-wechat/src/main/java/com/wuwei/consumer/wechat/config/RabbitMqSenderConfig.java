package com.wuwei.consumer.wechat.config;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSenderConfig {

    @Bean
    public Queue xiLeWangHistoryInsert(){
        return new Queue("xilewang_history_insert");
    }

}
