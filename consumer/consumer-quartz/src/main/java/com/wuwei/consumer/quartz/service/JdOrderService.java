package com.wuwei.consumer.quartz.service;

import com.wuwei.base.jd.service.OrderService;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.JdOrderServiceHystric;
import feign.RequestLine;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-jd",configuration = FeignConfig.class, fallback= JdOrderServiceHystric.class, path = "/order")
public interface JdOrderService extends OrderService {

    @Override
    @RequestLine("POST /")
    UnionOpenOrderQueryResponse query(@RequestBody OrderReq orderReq);
}
