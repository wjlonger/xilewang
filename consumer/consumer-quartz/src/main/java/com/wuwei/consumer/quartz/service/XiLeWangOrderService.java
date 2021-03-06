package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangOrderServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangOrderServiceHystric.class, path = "/xilewang/order")
public interface XiLeWangOrderService{

    @RequestLine("GET /{id}")
    XiLeWangOrder selectByPrimaryKey(@Param("id") Long id);

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangOrder xiLeWangOrder);

    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangOrder xiLeWangOrder);

}
