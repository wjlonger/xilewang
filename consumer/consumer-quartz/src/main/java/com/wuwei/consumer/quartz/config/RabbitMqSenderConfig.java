package com.wuwei.consumer.quartz.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSenderConfig {

    @Bean
    public Queue quartzJdOrderSave(){
        return new Queue("quartz_jdorder_save");
    }

    @Bean
    public Queue quartzSkuRebatePrice(){
        return new Queue("quartz_sku_rebate_price");
    }

    @Bean
    public Queue quartzIncomeReportSave(){
        return new Queue("quartz_income_report_save");
    }

    @Bean
    public Queue quartzBalanceSave(){
        return new Queue("quartz_balance_save");
    }
}
