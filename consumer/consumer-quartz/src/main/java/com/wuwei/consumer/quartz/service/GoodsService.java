package com.wuwei.consumer.quartz.service;

import com.wuwei.consumer.quartz.hystric.GoodsServiceHystric;
import feign.Param;
import feign.RequestLine;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= GoodsServiceHystric.class,path = "/goods")
public interface GoodsService extends com.wuwei.base.jd.service.GoodsService {

    @Override
    @RequestLine("GET /{skuId}")
    GoodsResp goodsDetail(@Param("skuId") Long skuId);
}
