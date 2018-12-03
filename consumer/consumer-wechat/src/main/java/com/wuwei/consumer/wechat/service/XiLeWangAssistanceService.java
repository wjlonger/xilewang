package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.consumer.wechat.config.FeignConfig;
import com.wuwei.consumer.wechat.hystric.XiLeWangAssistanceServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PreDestroy;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangAssistanceServiceHystric.class, path = "/xilewang/assistance")
public interface XiLeWangAssistanceService extends com.wuwei.base.wechat.service.XiLeWangAssistanceService {

    @Override
    @RequestLine("POST /")
    int insertSelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);

    @Override
    @RequestLine("GET /{id}")
    XiLeWangAssistance selectByPrimaryKey(@Param("id") final Long id);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangAssistance xiLeWangAssistance);

    @Override
    @RequestLine("GET /{openId}/{skuId}")
    XiLeWangAssistance selectByOpenIdAndSkuId(@Param("openId") final String openId, @Param("skuId") final Long skuId);

}
