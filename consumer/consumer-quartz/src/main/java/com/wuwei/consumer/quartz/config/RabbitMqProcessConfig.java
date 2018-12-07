package com.wuwei.consumer.quartz.config;

import com.wuwei.consumer.quartz.service.JdOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqProcessConfig {

    @Autowired
    private JdOrderService jdOrderService;

    @RabbitListener(queues = "quartz_jdorder_save")
    public void quartzJdOrderSave(){
        System.out.println("抓取订单并保存");
    }

}
