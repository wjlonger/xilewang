package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.consumer.wechat.hystric.XiLeWangJdOrderServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "provider-wechat",fallback= XiLeWangJdOrderServiceHystric.class, path = "/xilewang/jdorder")
public interface XiLeWangJdOrderService {

    @RequestLine("GET /selectByOpenid/{openid}")
    List<XiLeWangJdOrder> selectByOpenid(@Param("openid") String openid);

}
