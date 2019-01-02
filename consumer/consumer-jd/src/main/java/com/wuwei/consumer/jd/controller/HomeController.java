package com.wuwei.consumer.jd.controller;

import com.wuwei.consumer.jd.service.HomeService;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/jd")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Value("${goods.ratio}")
    private BigDecimal ratio;

    @Value("${math.scale}")
    private int scale;

    @GetMapping("/slideShow")
    public String slideShow(){
        return homeService.slideShow();
    }

    @PostMapping("/explosiveGoods")
    public UnionOpenGoodsQueryResponse explosiveGoods(@RequestBody GoodsReq goodsReq){
        BigDecimal percent = new BigDecimal(100);
        UnionOpenGoodsQueryResponse unionOpenGoodsQueryResponse = homeService.explosiveGoods(goodsReq);
        if(null != unionOpenGoodsQueryResponse){
            GoodsResp[] goodsResps = unionOpenGoodsQueryResponse.getData();
            if(null != goodsResps && goodsResps.length > 0){
                for(GoodsResp goodsResp : goodsResps){
                    if(null != goodsResp){
                        CommissionInfo[] commissionInfos = goodsResp.getCommissionInfo();
                        if(null != commissionInfos && commissionInfos.length > 0){
                            for(CommissionInfo commissionInfo : commissionInfos){
                                if(null != commissionInfo){
                                    PinGouInfo[] pinGouInfos = goodsResp.getPinGouInfo();
                                    PinGouInfo pinGouInfo = null;
                                    if(null != pinGouInfos && pinGouInfos.length > 0){
                                        pinGouInfo = pinGouInfos[0];
                                    }
                                    if(null != pinGouInfo && null != pinGouInfo.getPingouPrice()){
                                        commissionInfo.setCommission(new BigDecimal(pinGouInfo.getPingouPrice()).multiply(new BigDecimal(commissionInfo.getCommissionShare())).multiply(ratio).divide(percent).divide(percent).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue());
                                    }else{
                                        commissionInfo.setCommission(new BigDecimal(commissionInfo.getCommission()).multiply(ratio).divide(percent).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue());
                                    }
                                    if(commissionInfo.getCommission() < 0.01){
                                        commissionInfo.setCommission(0.01);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return unionOpenGoodsQueryResponse;
    }
}
