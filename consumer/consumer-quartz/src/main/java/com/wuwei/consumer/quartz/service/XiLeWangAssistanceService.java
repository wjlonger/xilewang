package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangAssistanceServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangAssistanceServiceHystric.class, path = "/xilewang/assistance")
public interface XiLeWangAssistanceService extends com.wuwei.base.wechat.service.XiLeWangAssistanceService {

    @Override
    @RequestLine("POST /")
    int insertSelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);

    @Override
    @RequestLine("GET /{id}")
    XiLeWangAssistance selectByPrimaryKey(@PathVariable("id") Long id);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);

    @Override
    @RequestLine("GET /{openId}/{skuId}")
    XiLeWangAssistance selectByOpenIdAndSkuId(@PathVariable("openId") String openId, @PathVariable("skuId") Long skuId);
}
