package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangJdOrderServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangJdOrderServiceHystric.class, path = "/xilewang/jdorder")
public interface XiLeWangJdOrderService{

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangJdOrder xiLeWangJdOrder);

    @RequestLine("GET /{orderId}")
    XiLeWangJdOrder selectByPrimaryKey(@Param("orderId") Long orderId);

    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangJdOrder xiLeWangJdOrder);

}
