package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangOrder;

import java.util.List;

public interface XiLeWangOrderService {

    XiLeWangOrder selectByPrimaryKey(Long id);

    int insert(XiLeWangOrder xiLeWangOrder);

    int insertSelective(XiLeWangOrder xiLeWangOrder);

    int updateByPrimaryKeySelective(XiLeWangOrder xiLeWangOrder);

    int updateByPrimaryKey(XiLeWangOrder xiLeWangOrder);

}
