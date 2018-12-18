package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.consumer.wechat.config.FeignConfig;
import com.wuwei.consumer.wechat.hystric.XiLeWangAssistanceUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangAssistanceUserServiceHystric.class, path = "/xilewang/assistance/user")
public interface XiLeWangAssistanceUserService{

    @RequestLine("GET /{assistanceId}")
    List<XiLeWangAssistanceUser> selectByAssistanceId(@Param("assistanceId") Long assistanceId);

    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangAssistanceUser xiLeWangAssistanceUser);

}
