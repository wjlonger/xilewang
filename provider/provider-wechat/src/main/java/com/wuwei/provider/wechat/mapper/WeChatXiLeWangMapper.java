package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.WeChatXiLeWang;
import org.apache.ibatis.annotations.Param;

public interface WeChatXiLeWangMapper {
    int deleteByPrimaryKey(@Param("openid") String openid, @Param("sessionkey") String sessionkey);

    int insert(WeChatXiLeWang record);

    int insertSelective(WeChatXiLeWang record);
}