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
                                                            price -= coupon.getQuota();
                                                            break coupon;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    commissionInfo.setCommission(new BigDecimal(price).multiply(new BigDecimal(commissionInfo.getCommissionShare())).divide(percent).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue());
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
