package com.wuwei.consumer.wechat.hystric;

import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangGoodsServiceHystric implements XiLeWangGoodsService {

    @Override
    public GoodsResp goodsDetail(Long skuId) {
        return null;
    }
}
