package com.wuwei.consumer.jd.controller;

import com.wuwei.base.jd.model.Goods;
import com.wuwei.consumer.jd.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

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
    public String explosiveGoods(@RequestBody Goods goods){
        return homeService.explosiveGoods(goods);
    }

}
