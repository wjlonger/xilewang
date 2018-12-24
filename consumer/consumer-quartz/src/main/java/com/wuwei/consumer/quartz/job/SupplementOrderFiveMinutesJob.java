package com.wuwei.consumer.quartz.job;

import com.wuwei.base.util.DateUtils;
import jd.union.open.order.query.request.OrderReq;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class SupplementOrderFiveMinutesJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String time = DateUtils.getOrderTimeForJd(-5);
        System.out.println(time + "   开始抓取订单");
        OrderReq orderReq = new OrderReq();
        orderReq.setPageNo(1);
        orderReq.setPageSize(500);
        orderReq.setType(3);
        orderReq.setTime(time);
        amqpTemplate.convertAndSend("quartz_supplement_jdorder_save",orderReq);
    }

}
