package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangUser;
import org.apache.ibatis.annotations.Param;

public interface XiLeWangUserMapper {

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    XiLeWangUser selectByPrimaryKey(@Param("openid") String openid);

    int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser);

    int updateByPrimaryKey(XiLeWangUser xiLeWangUser);

}