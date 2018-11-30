package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangUser;

public interface XiLeWangUserService {

    String code2Session(String code);

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    XiLeWangUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser);

    int updateByPrimaryKey(XiLeWangUser xiLeWangUser);

    int save(XiLeWangUser xiLeWangUser);




}
