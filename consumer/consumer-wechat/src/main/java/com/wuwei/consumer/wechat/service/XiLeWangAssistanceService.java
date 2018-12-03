package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.consumer.wechat.hystric.XiLeWangAssistanceServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangAssistanceServiceHystric.class, path = "/xilewang/assistance")
public interface XiLeWangAssistanceService extends com.wuwei.base.wechat.service.XiLeWangAssistanceService {

    @Override
    @RequestLine("POST /")
    int insertSelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);

    @Override
    @RequestLine("GET /{id}")
    XiLeWangAssistance selectByPrimaryKey(@Param("id") Long id);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);
}
