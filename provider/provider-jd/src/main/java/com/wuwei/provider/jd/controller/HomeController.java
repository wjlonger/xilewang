package com.wuwei.provider.jd.controller;

import com.wuwei.base.jd.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/slideShow")
    public String slideShow(){
        return homeService.slideShow();
    }

    @GetMapping("/explosiveGoods")
    public String explosiveGoods() {
        return homeService.explosiveGoods();
    }

}
