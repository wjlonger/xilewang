package com.wuwei.consumer.jd.service;

import com.wuwei.consumer.jd.hystric.GoodsServiceHystric;
import feign.Param;
import feign.RequestLine;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= GoodsServiceHystric.class,path = "/goods")
public interface GoodsService{

    @RequestLine("GET /{skuId}")
    GoodsResp goodsDetail(@Param("skuId") Long skuId);
}
