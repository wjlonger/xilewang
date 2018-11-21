package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangUser;

public interface XiLeWangUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XiLeWangUser record);

    int insertSelective(XiLeWangUser record);

    XiLeWangUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangUser record);

    int updateByPrimaryKey(XiLeWangUser record);
}