package com.wuwei.consumer.jd.service;

import com.wuwei.consumer.jd.hystric.HomeServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= HomeServiceHystric.class)
public interface HomeService extends com.wuwei.base.jd.service.HomeService {

    @Override
    @RequestLine("GET /slideShow")
    String slideShow();

}
