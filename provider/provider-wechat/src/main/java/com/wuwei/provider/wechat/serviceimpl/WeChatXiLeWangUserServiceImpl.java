package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.wechat.model.WeChatXiLeWangUser;
import com.wuwei.base.wechat.service.WeChatXiLeWangUserService;
import com.wuwei.provider.wechat.mapper.WeChatXiLeWangUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("weChatXiLeWangUserService")
public class WeChatXiLeWangUserServiceImpl implements WeChatXiLeWangUserService {

    @Autowired
    private WeChatXiLeWangUserMapper weChatXiLeWangUserMapper;

    @Override
    public WeChatXiLeWangUser SelectByOpenid(String openid) {
        return weChatXiLeWangUserMapper.SelectByOpenid(openid);
    }
}
