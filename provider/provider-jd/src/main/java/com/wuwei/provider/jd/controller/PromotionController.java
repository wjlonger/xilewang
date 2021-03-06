package com.wuwei.provider.jd.controller;

import com.wuwei.base.jd.service.PromotionService;
import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/getBySubUnionId")
    public String getBySubUnionId(@RequestBody PromotionCodeReq promotionCodeReq){
        return promotionService.getBySubUnionId(promotionCodeReq);
    }

}
