package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;

import java.util.List;

public interface XiLeWangJdOrderService {

    int insert(XiLeWangJdOrder xiLeWangJdOrder);

    int insertSelective(XiLeWangJdOrder xiLeWangJdOrder);

    XiLeWangJdOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(XiLeWangJdOrder xiLeWangJdOrder);

    int updateByPrimaryKey(XiLeWangJdOrder xiLeWangJdOrder);

    List<XiLeWangJdOrder> selectByOpenid(String openid);

}
