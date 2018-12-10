package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangOrderServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangOrderServiceHystric.class, path = "/xilewang/order")
public interface XiLeWangOrderService extends com.wuwei.base.wechat.service.XiLeWangOrderService {

    @Override
    @RequestLine("GET /{id}")
    XiLeWangOrder selectByPrimaryKey(@PathVariable("id") Long id);

    @Override
    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangOrder xiLeWangOrder);

    @Override
    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangOrder xiLeWangOrder);

    @Override
    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangOrder xiLeWangOrder);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKey(@RequestBody XiLeWangOrder xiLeWangOrder);
}
