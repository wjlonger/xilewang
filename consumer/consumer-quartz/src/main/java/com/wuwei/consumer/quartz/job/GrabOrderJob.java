package com.wuwei.consumer.quartz.job;

import com.wuwei.base.util.CollectionUtils;
import com.wuwei.base.util.DateUtils;
import com.wuwei.consumer.quartz.service.JdOrderService;
import com.wuwei.consumer.quartz.service.XiLeWangQuartzService;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.OrderResp;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class GrabOrderJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始抓取订单");
        amqpTemplate.convertAndSend("quartz_jdorder_save",DateUtils.getOrderTimeForJd());
    }

}
