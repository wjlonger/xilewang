package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.hystric.XiLeWangOrderServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangOrderServiceHystric.class, path = "/xilewang/order")
public interface XiLeWangOrderService {

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangOrder xiLeWangOrder);

}
