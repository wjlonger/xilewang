package com.wuwei.consumer.wechat.hystric;

import com.github.pagehelper.PageInfo;
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
    public int insertSelective(XiLeWangUser xiLeWangUser) {
        return 0;
    }

    @Override
    public int save(XiLeWangUser xiLeWangUser) {
        return 0;
    }

    @Override
    public PageInfo<XiLeWangUser> listByMasterOpenid(String openid, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Integer inviteCount(String openid) {
        return null;
    }

}
