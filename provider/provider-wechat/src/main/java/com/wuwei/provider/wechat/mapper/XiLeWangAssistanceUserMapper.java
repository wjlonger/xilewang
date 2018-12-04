package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;

import java.util.List;

public interface XiLeWangAssistanceUserMapper {

    int insert(XiLeWangAssistanceUser xiLeWangAssistanceUser);

    int insertSelective(XiLeWangAssistanceUser xiLeWangAssistanceUser);

    XiLeWangAssistanceUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangAssistanceUser xiLeWangAssistanceUser);

    int updateByPrimaryKey(XiLeWangAssistanceUser xiLeWangAssistanceUser);

    List<XiLeWangAssistanceUser> selectByAssistanceId(Long assistanceId);
}