package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangUser;

public interface XiLeWangUserMapper {

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    XiLeWangUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser);

    int updateByPrimaryKey(XiLeWangUser xiLeWangUser);

}