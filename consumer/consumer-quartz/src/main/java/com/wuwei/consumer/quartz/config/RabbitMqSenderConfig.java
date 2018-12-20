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
    public Queue quartzIncomeReportInvalidSave(){
        return new Queue("quartz_income_report_invalid_save");
    }

    @Bean
    public Queue quartzIncomeReportValidSave(){
        return new Queue("quartz_income_report_valid_save");
    }

    @Bean
    public Queue quartzBalanceSave(){
        return new Queue("quartz_balance_save");
    }

    @Bean
    public Queue quartzSupplementJdorderSave(){
        return new Queue("quartz_supplement_jdorder_save");
    }
}
