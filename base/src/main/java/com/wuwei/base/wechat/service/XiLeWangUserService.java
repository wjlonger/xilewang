package com.wuwei.base.wechat.service;

import com.github.pagehelper.PageInfo;
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

    int inviteCount(String openid);

    PageInfo<XiLeWangUser> listByMasterOpenid(String openid, Integer pageNo, Integer pageSize);

}
