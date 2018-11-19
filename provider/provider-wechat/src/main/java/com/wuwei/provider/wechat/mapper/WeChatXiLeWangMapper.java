package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.WeChatXiLeWang;
import org.apache.ibatis.annotations.Param;

public interface WeChatXiLeWangMapper {

    int deleteByPrimaryKey(@Param("openid") String openid, @Param("sessionkey") String sessionkey);

    int insert(WeChatXiLeWang xiLeWang);

    int insertSelective(WeChatXiLeWang xiLeWang);

    WeChatXiLeWang selectByOpenid(@Param("openid") String openid);

    int updateByOpenid(WeChatXiLeWang weChatXiLeWang);
}