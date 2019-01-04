package com.wuwei.consumer.jd.controller;

import com.wuwei.consumer.jd.service.GoodsService;
import jd.union.open.goods.query.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/jd/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{skuId}")
    public GoodsResp goodsDetail(@PathVariable("skuId") Long skuId){
        GoodsResp goodsResp = goodsService.goodsDetail(skuId);
        if(null != goodsResp){
            CommissionInfo[] commissionInfos = goodsResp.getCommissionInfo();
            if(null != commissionInfos && commissionInfos.length > 0){
                for(CommissionInfo commissionInfo : commissionInfos){
                    if(null != commissionInfo){
                        double price = goodsResp.getPriceInfo()[0].getPrice();
                        PinGouInfo[] pinGouInfos = goodsResp.getPinGouInfo();
                        if(null != pinGouInfos && pinGouInfos.length > 0){
                            PinGouInfo pinGouInfo  = pinGouInfos[0];
                            if(null != pinGouInfo && null != pinGouInfo.getPingouPrice()){
                                price = pinGouInfo.getPingouPrice();
                            }
                        }
                        CouponInfo[] couponInfos = goodsResp.getCouponInfo();
                        if(null != couponInfos && couponInfos.length > 0){
                            CouponInfo couponInfo = couponInfos[0];
                            if(null != couponInfo){
                                Coupon[] coupons = couponInfo.getCouponList();
                                if(null != coupons && coupons.length > 0){
                                    coupon:
                                    for(Coupon coupon : coupons){
                                        if(null != coupon){
                                            if(price >= coupon.getQuota()){
                                                price -= coupon.getDiscount();
                                                break coupon;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        commissionInfo.setCommission(new BigDecimal(price).multiply(new BigDecimal(commissionInfo.getCommissionShare())).divide(BigDecimal.valueOf(100L)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                        if(commissionInfo.getCommission() < 0.01){
                            commissionInfo.setCommission(0.01);
                        }
                    }
                }
            }
        }
        return goodsResp;
    }

}
