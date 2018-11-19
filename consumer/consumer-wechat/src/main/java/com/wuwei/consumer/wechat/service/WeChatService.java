package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.WeChatXiLeWang;
import com.wuwei.consumer.wechat.hystric.WeChatServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-wechat",fallback= WeChatServiceHystric.class, path = "/wechat")
public interface WeChatService extends com.wuwei.base.wechat.service.WeChatService {

    @Override
    @RequestLine("GET /code2Session/{code}")
    WeChatXiLeWang code2Session(@Param("code") String code);
}
