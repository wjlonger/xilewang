package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangActivity;

import java.util.List;

public interface XiLeWangActivityMapper {

    int insertSelective(XiLeWangActivity xiLeWangActivity);

    XiLeWangActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangActivity xiLeWangActivity);

    List<XiLeWangActivity> listAll(XiLeWangActivity xiLeWangActivity);
}