package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import com.wuwei.consumer.wechat.service.XiLeWangPromotionService;
import com.wuwei.consumer.wechat.utils.Current;
import jd.union.open.goods.query.response.*;
import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/goods")
public class XiLeWangGoodsController {

    @Autowired
    private XiLeWangPromotionService xiLeWangPromotionService;

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    @Value("${goods.ratio}")
    private BigDecimal ratio;

    @Value("${spring.cloud.config.profile}")
    private String edition;

    @Value("${math.scale}")
    private int scale;

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/detail/{skuId}")
    public JSONObject detail(@PathVariable("skuId") Long skuId ){
        GoodsResp goodsResp = xiLeWangGoodsService.goodsDetail(skuId);
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
                                goodsResp.getPriceInfo()[0].setPrice(price);
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
        jsonObject.put("ratio",this.ratio);
        jsonObject.put("goods",goodsResp);
        return jsonObject;
    }

    @GetMapping("/{skuId}")
    public JSONObject buy(@PathVariable("skuId") Long skuId, @RequestParam(name="couponUrl",required = false) String couponUrl){
        long id = IdGenerator.nextId();
        PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
        promotionCodeReq.setSubUnionId(edition + id);
        promotionCodeReq.setMaterialId(String.format("https://wqitem.jd.com/item/view?sku=%d",skuId));
        promotionCodeReq.setCouponUrl(couponUrl);
        String url = xiLeWangPromotionService.getBySubUnionId(promotionCodeReq);
        if(StringUtils.isEmpty(url)){
            promotionCodeReq.setCouponUrl(null);
            url = xiLeWangPromotionService.getBySubUnionId(promotionCodeReq);
        }
        if(!StringUtils.isEmpty(url)){
            XiLeWangOrder xiLeWangOrder = new XiLeWangOrder();
            xiLeWangOrder.setId(id);
            xiLeWangOrder.setSkuId(skuId);
            xiLeWangOrder.setOpenid(Current.getOpenid());
            xiLeWangOrder.setUrl(url);
            xiLeWangOrder.setInitialRatio(this.ratio);
            xiLeWangOrder.setAssistanceId(0L);
            int i = xiLeWangOrderService.insertSelective(xiLeWangOrder);
            if(i > 0){
                jsonObject.put("code",1);
                jsonObject.put("url",url);
            }else{
                jsonObject.put("code",0);
                jsonObject.put("url", com.wuwei.base.util.StringUtils.EMPTY);
            }
        }else{
            jsonObject.put("code",0);
            jsonObject.put("url", com.wuwei.base.util.StringUtils.EMPTY);
        }
        return jsonObject;
    }
}
