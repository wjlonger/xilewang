package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangUserServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangUserServiceHystric.class, path = "/xilewang/user")
public interface XiLeWangUserService  {

    @RequestLine("GET /{openid}")
    XiLeWangUser selectByPrimaryKey(@PathVariable("openid") String openid);

}
