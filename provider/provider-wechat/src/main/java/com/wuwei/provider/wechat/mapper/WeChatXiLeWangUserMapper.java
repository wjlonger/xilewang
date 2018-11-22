package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.WeChatXiLeWangUser;
import org.apache.ibatis.annotations.Param;

public interface WeChatXiLeWangUserMapper {

    WeChatXiLeWangUser SelectByOpenid(@Param("openid") String openid);

}