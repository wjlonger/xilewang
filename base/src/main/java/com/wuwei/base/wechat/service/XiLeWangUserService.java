package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangUser;

public interface XiLeWangUserService {

    String code2Session(String code);

    XiLeWangUser selectByOpenid(String openid);

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    int updateByOpenid(XiLeWangUser xiLeWangUser);

    int save(XiLeWangUser xiLeWangUser);

}
