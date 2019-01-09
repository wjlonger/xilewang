package com.wuwei.consumer.wechat.hystric;

import com.wuwei.consumer.wechat.service.XiLeWangWeChatAccessTokenService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangWeChatAccessTokenServiceHystric implements XiLeWangWeChatAccessTokenService {

    @Override
    public String getAccessToken() {
        return null;
    }

}
