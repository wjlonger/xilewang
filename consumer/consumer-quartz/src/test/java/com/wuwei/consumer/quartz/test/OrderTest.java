package com.wuwei.consumer.quartz.test;

import com.wuwei.base.util.DateUtils;
import com.wuwei.base.wechat.model.XiLeWangUser;
import jd.union.open.order.query.request.OrderReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testOrder(){
        OrderReq orderReq = new OrderReq();
        orderReq.setPageNo(1);
        orderReq.setPageSize(500);
        orderReq.setType(1);
        orderReq.setTime("2018121111");
        amqpTemplate.convertAndSend("quartz_jdorder_save",orderReq);
    }

}
