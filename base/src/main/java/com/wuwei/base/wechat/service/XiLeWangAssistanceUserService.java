package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;

import java.util.List;

public interface XiLeWangAssistanceUserService {

    List<XiLeWangAssistanceUser> selectByAssistanceId(Long assistanceId);

    int insert(XiLeWangAssistanceUser xiLeWangAssistanceUser);

}
