package com.wuwei.consumer.jd.service;

import com.wuwei.consumer.jd.hystric.GoodsServiceHystric;
import feign.Param;
import feign.RequestLine;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= GoodsServiceHystric.class)
public interface GoodsService extends com.wuwei.base.jd.service.GoodsService {

    @Override
    @RequestLine("GET /{skuId}")
    GoodsResp goodsDetail(@Param("skuId") Long skuId);
}
