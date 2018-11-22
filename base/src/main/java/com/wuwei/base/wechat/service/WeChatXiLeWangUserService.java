package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.WeChatXiLeWangUser;

/**
 * @author buzai
 * @createtime  2018年11月22日11:16:59
 */
public interface WeChatXiLeWangUserService {

    WeChatXiLeWangUser SelectByOpenid(String openid);

}
