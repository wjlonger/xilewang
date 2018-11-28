package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangOrderServiceHystric implements XiLeWangOrderService {

    @Override
    public XiLeWangOrder selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int insert(XiLeWangOrder xiLeWangOrder) {
        return 0;
    }

    @Override
    public int insertSelective(XiLeWangOrder xiLeWangOrder) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangOrder xiLeWangOrder) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(XiLeWangOrder xiLeWangOrder) {
        return 0;
    }
}
