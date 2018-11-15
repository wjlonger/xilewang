package com.wuwei.consumer.jd.service;

import com.wuwei.consumer.jd.hystric.GoodsServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= GoodsServiceHystric.class,path = "/goods")
public interface GoodsService extends com.wuwei.base.jd.service.GoodsService {

    @Override
    @RequestLine("GET /{goodsId}")
    String goodsDetail(@Param("goodsId") String goodsId);

}
