package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangUser;
import org.apache.ibatis.annotations.Param;

public interface XiLeWangUserMapper {

    XiLeWangUser selectByOpenid(@Param("openid")String openid);

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    int updateByOpenid(XiLeWangUser xiLeWangUser);

}