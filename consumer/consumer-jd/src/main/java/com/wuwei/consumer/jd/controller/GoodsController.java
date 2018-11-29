package com.wuwei.consumer.jd.controller;

import com.wuwei.consumer.jd.service.GoodsService;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/jd/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{skuId}")
    public GoodsResp goodsDetail(@PathVariable("skuId") Long skuId){
        return goodsService.goodsDetail(skuId);
    }

}
