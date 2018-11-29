package com.wuwei.consumer.jd.hystric;

import com.wuwei.consumer.jd.service.GoodsService;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodsServiceHystric implements GoodsService {

    @Override
    public GoodsResp goodsDetail(Long skuId) {
        return null;
    }

}
