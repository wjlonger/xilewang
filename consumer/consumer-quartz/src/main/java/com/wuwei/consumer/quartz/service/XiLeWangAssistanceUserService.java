package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangAssistanceUserServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangAssistanceUserServiceHystric.class, path = "/xilewang/assistance/user")
public interface XiLeWangAssistanceUserService extends com.wuwei.base.wechat.service.XiLeWangAssistanceUserService {

    @Override
    @RequestLine("GET /{assistanceId}")
    List<XiLeWangAssistanceUser> selectByAssistanceId(@PathVariable("assistanceId") Long assistanceId);

    @Override
    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangAssistanceUser xiLeWangAssistanceUser);
}
