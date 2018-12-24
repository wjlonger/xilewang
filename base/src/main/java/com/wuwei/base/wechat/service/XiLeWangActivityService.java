package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangActivity;

import java.util.List;

public interface XiLeWangActivityService {

    int insertSelective(XiLeWangActivity xiLeWangActivity);

    XiLeWangActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangActivity xiLeWangActivity);

    List<XiLeWangActivity> listAll(XiLeWangActivity xiLeWangActivity);

}
