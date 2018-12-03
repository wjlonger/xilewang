package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.hystric.XiLeWangOrderServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangOrderServiceHystric.class, path = "/xilewang/order")
public interface XiLeWangOrderService extends com.wuwei.base.wechat.service.XiLeWangOrderService {

    @Override
    @RequestLine("GET /{id}")
    XiLeWangOrder selectByPrimaryKey(@Param("id") final Long id);

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
