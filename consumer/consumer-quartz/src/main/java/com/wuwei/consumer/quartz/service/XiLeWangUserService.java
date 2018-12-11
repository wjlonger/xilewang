package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.math.BigDecimal;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangUserServiceHystric.class, path = "/xilewang/user")
public interface XiLeWangUserService  {

    @RequestLine("GET /{openid}")
    XiLeWangUser selectByPrimaryKey(@Param("openid") String openid);

    @RequestLine("POST /updateMoneyByPrimaryKey")
    int updateMoneyByPrimaryKey(int type, BigDecimal modifyMoney, String openid);
}
