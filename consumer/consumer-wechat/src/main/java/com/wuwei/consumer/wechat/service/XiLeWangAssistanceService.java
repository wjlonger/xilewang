package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.consumer.wechat.config.FeignConfig;
import com.wuwei.consumer.wechat.hystric.XiLeWangAssistanceServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangAssistanceServiceHystric.class, path = "/xilewang/assistance")
public interface XiLeWangAssistanceService{

    @RequestLine("POST /")
    int insertSelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);

    @RequestLine("GET /{id}")
    XiLeWangAssistance selectByPrimaryKey(@Param("id") Long id);

    @RequestLine("GET /selectByOpenIdAndSkuId/{openId}/{skuId}")
    XiLeWangAssistance selectByOpenIdAndSkuId(@Param("openId") String openId, @Param("skuId") Long skuId);

}
