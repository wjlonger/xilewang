package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangJdOrderServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangJdOrderServiceHystric.class, path = "/xilewang/jdorder")
public interface XiLeWangJdOrderService extends com.wuwei.base.wechat.service.XiLeWangJdOrderService {

    @Override
    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangJdOrder xiLeWangJdOrder);

    @Override
    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangJdOrder xiLeWangJdOrder);

    @Override
    @RequestLine("GET /{orderId}")
    XiLeWangJdOrder selectByPrimaryKey(@PathVariable("orderId") Long orderId);

    @Override
    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangJdOrder xiLeWangJdOrder);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKey(@RequestBody XiLeWangJdOrder xiLeWangJdOrder);
}
