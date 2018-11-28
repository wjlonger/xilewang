package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangOrder;

public interface XiLeWangOrderMapper {

    XiLeWangOrder selectByPrimaryKey(Long id);

    int insert(XiLeWangOrder xiLeWangOrder);

    int insertSelective(XiLeWangOrder xiLeWangOrder);

    int updateByPrimaryKeySelective(XiLeWangOrder xiLeWangOrder);

    int updateByPrimaryKey(XiLeWangOrder xiLeWangOrder);
}