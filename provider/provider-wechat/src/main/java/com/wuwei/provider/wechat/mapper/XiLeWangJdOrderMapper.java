package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;

public interface XiLeWangJdOrderMapper {

    int insert(XiLeWangJdOrder xiLeWangJdOrder);

    int insertSelective(XiLeWangJdOrder xiLeWangJdOrder);

    XiLeWangJdOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(XiLeWangJdOrder xiLeWangJdOrder);

    int updateByPrimaryKey(XiLeWangJdOrder xiLeWangJdOrder);
}