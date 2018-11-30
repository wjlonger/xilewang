package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangAssistance;

public interface XiLeWangAssistanceMapper {

    int insert(XiLeWangAssistance xiLeWangAssistance);

    int insertSelective(XiLeWangAssistance xiLeWangAssistance);

    XiLeWangAssistance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance);

    int updateByPrimaryKey(XiLeWangAssistance xiLeWangAssistance);
}