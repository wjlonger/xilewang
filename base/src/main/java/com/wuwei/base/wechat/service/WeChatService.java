package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.WeChatXiLeWang;

public interface WeChatService {

    WeChatXiLeWang code2Session(String code);

}
