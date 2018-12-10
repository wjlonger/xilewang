package com.wuwei.consumer.wechat.service;

import com.wuwei.base.jd.service.GoodsService;
import com.wuwei.consumer.wechat.hystric.XiLeWangGoodsServiceHystric;
import feign.Param;
import feign.RequestLine;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-jd",fallback= XiLeWangGoodsServiceHystric.class, path = "/goods")
public interface XiLeWangGoodsService{

    @RequestLine("GET /{skuId}")
    GoodsResp goodsDetail(@Param("skuId") final Long skuId);

}
