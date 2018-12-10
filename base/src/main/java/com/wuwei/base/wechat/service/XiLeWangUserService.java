package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangUser;

import java.math.BigDecimal;

public interface XiLeWangUserService {

    String code2Session(String code,String inviteCode);

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    XiLeWangUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser);

    int updateByPrimaryKey(XiLeWangUser xiLeWangUser);

    int save(XiLeWangUser xiLeWangUser);

    int updateMoneyByPrimaryKey(int type, BigDecimal modifyMoney, String openid);

}
