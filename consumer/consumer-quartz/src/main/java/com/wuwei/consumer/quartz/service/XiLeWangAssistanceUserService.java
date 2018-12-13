package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangAssistanceUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangAssistanceUserServiceHystric.class, path = "/xilewang/assistance/user")
public interface XiLeWangAssistanceUserService{

    @RequestLine("GET /{assistanceId}")
    List<XiLeWangAssistanceUser> selectByAssistanceId(@Param("assistanceId") Long assistanceId);

}
