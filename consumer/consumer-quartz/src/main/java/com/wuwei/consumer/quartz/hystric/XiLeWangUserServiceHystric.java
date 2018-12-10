package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.quartz.service.XiLeWangUserService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangUserServiceHystric implements XiLeWangUserService {

    @Override
    public XiLeWangUser selectByPrimaryKey(String openid) {
        return null;
    }

}
