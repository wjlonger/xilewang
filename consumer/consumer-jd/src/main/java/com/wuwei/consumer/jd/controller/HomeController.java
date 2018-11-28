package com.wuwei.consumer.jd.controller;

import com.wuwei.base.jd.model.GoodsSearch;
import com.wuwei.consumer.jd.service.HomeService;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/jd")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/slideShow")
    public String slideShow(){
        return homeService.slideShow();
    }

    @PostMapping("/explosiveGoods")
    public UnionOpenGoodsQueryResponse explosiveGoods(@RequestBody GoodsSearch goodsSearch){
        return homeService.explosiveGoods(goodsSearch);
    }
}
