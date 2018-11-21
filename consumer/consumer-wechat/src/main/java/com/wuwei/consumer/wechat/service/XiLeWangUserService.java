package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.hystric.WeChatServiceHystric;
import com.wuwei.consumer.wechat.hystric.XiLeWangUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangUserServiceHystric.class, path = "/xilewang/user")
public interface XiLeWangUserService extends com.wuwei.base.wechat.service.XiLeWangUserService{

    @Override
    @RequestLine("GET /{id}")
    XiLeWangUser selectById(@Param("id") Long id);

    @Override
    @RequestLine("POST /")
    XiLeWangUser insert(@RequestBody XiLeWangUser xiLeWangUser);

    @Override
    @RequestLine("PUT /")
    XiLeWangUser updateById(@RequestBody XiLeWangUser xiLeWangUser);

    @Override
    @RequestLine("POST /save")
    XiLeWangUser save(@RequestBody XiLeWangUser xiLeWangUser);
}
