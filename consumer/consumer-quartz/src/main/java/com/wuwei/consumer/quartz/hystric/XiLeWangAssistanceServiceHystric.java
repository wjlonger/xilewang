package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.consumer.quartz.service.XiLeWangAssistanceService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangAssistanceServiceHystric implements XiLeWangAssistanceService {

    @Override
    public int insertSelective(XiLeWangAssistance xiLeWangAssistance) {
        return 0;
    }

    @Override
    public XiLeWangAssistance selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance) {
        return 0;
    }

    @Override
    public XiLeWangAssistance selectByOpenIdAndSkuId(String openId, Long skuId) {
        return null;
    }
}
