package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangUserServiceHystric implements XiLeWangUserService {

    @Override
    public String code2Session(String code, String inviteCode) {
        return null;
    }

    @Override
    public XiLeWangUser selectByPrimaryKey(String openid) {
        return null;
    }

    @Override
    public int insert(XiLeWangUser xiLeWangUser) {
        return 0;
    }

    @Override
    public int insertSelective(XiLeWangUser xiLeWangUser) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(XiLeWangUser xiLeWangUser) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser) {
        return 0;
    }

    @Override
    public int save(XiLeWangUser xiLeWangUser) {
        return 0;
    }
}
