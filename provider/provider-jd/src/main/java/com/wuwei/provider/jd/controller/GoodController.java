package com.wuwei.provider.jd.controller;

import com.wuwei.base.jd.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{goodsId}")
    public String goodDetail(@PathVariable("goodsId") String goodsId) {
        return goodsService.goodsDetail(goodsId);
    }

}
