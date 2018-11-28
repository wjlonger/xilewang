package com.wuwei.consumer.jd.service;

import com.wuwei.base.jd.model.GoodsSearch;
import com.wuwei.consumer.jd.hystric.HomeServiceHystric;
import feign.RequestLine;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-jd",fallback= HomeServiceHystric.class)
public interface HomeService extends com.wuwei.base.jd.service.HomeService {

    @Override
    @RequestLine("GET /slideShow")
    String slideShow();

    @Override
    @RequestLine("POST /explosiveGoods")
    UnionOpenGoodsQueryResponse explosiveGoods(@RequestBody GoodsSearch goodsSearch);
}
