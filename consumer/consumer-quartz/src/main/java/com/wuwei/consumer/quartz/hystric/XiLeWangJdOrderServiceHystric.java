package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.consumer.quartz.service.XiLeWangJdOrderService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangJdOrderServiceHystric implements XiLeWangJdOrderService {

    @Override
    public int insertSelective(XiLeWangJdOrder xiLeWangJdOrder) {
        return 0;
    }

    @Override
    public XiLeWangJdOrder selectByPrimaryKey(Long orderId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangJdOrder xiLeWangJdOrder) {
        return 0;
    }

}
