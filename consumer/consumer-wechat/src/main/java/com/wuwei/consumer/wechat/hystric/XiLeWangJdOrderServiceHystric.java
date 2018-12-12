package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.consumer.wechat.service.XiLeWangJdOrderService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class XiLeWangJdOrderServiceHystric implements XiLeWangJdOrderService {

    @Override
    public List<XiLeWangJdOrder> selectByOpenid(String openid) {
        return null;
    }

}
