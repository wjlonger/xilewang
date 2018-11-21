package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangUser;
import org.apache.ibatis.annotations.Param;

public interface XiLeWangUserMapper {

    XiLeWangUser selectById(@Param("id")Long id);

    int insert(XiLeWangUser xiLeWangUser);

    int updateById(XiLeWangUser xiLeWangUser);

}