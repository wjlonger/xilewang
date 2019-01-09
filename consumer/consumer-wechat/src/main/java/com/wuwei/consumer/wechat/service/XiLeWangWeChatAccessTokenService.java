package com.wuwei.consumer.wechat.service;

import com.wuwei.consumer.wechat.hystric.XiLeWangWeChatAccessTokenServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-wechat",fallback= XiLeWangWeChatAccessTokenServiceHystric.class, path = "/xilewang/accesstoken")
public interface XiLeWangWeChatAccessTokenService {

    @RequestLine("GET /")
    String getAccessToken();

}
