package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangAssistance;

public interface XiLeWangAssistanceService {

    int insertSelective(XiLeWangAssistance xiLeWangAssistance);

    XiLeWangAssistance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance);

}
