package com.wuwei.consumer.jd.service;

import com.wuwei.consumer.jd.hystric.GoodServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= GoodServiceHystric.class,path = "/good")
public interface GoodService extends com.wuwei.base.jd.service.GoodService {

    @Override
    @RequestLine("GET /{goodId}")
    String goodDetail(@Param("goodId") int goodId);

}
