package com.wuwei.consumer.jd.service;

import com.wuwei.consumer.jd.hystric.HomeServiceHystric;
import feign.RequestLine;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-jd",fallback= HomeServiceHystric.class)
public interface HomeService{

    @RequestLine("GET /slideShow")
    String slideShow();

    @RequestLine("POST /explosiveGoods")
    UnionOpenGoodsQueryResponse explosiveGoods(@RequestBody GoodsReq goodsReq);
}
