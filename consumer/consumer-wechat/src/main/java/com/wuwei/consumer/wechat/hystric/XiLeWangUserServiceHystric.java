package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangUserServiceHystric implements XiLeWangUserService {
    @Override
    public XiLeWangUser selectById(Long id) {
        return null;
    }

    @Override
    public XiLeWangUser insert(XiLeWangUser xiLeWangUser) {
        return xiLeWangUser;
    }

    @Override
    public XiLeWangUser updateById(XiLeWangUser xiLeWangUser) {
        return xiLeWangUser;
    }

    @Override
    public XiLeWangUser save(XiLeWangUser xiLeWangUser) {
        return xiLeWangUser;
    }
}
