package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.util.CollectionUtils;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.*;
import com.wuwei.consumer.wechat.utils.Current;
import jd.union.open.goods.query.response.GoodsResp;
import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/goods")
public class XiLeWangGoodsController {

    @Autowired
    private XiLeWangPromotionService xiLeWangPromotionService;

    @Autowired
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    @Autowired
    private XiLeWangAssistanceUserService xiLeWangAssistanceUserService;

    @Value("${goods.ratio}")
    private BigDecimal ratio;

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/detail/{skuId}")
    public JSONObject detail(@PathVariable("skuId") Long skuId ){
        GoodsResp goodsResp = xiLeWangGoodsService.goodsDetail(skuId);
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByOpenIdAndSkuId(Current.getOpenid(),skuId);
        List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = null;
        if(null != xiLeWangAssistance){
            jsonObject.put("assistance",xiLeWangAssistance.getId());
            xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangAssistance.getId());
            if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                jsonObject.put("users",xiLeWangAssistanceUsers);
            }else{
                jsonObject.put("users",new ArrayList<>());
            }
        }else{
            jsonObject.put("assistance",IdGenerator.nextId());
            jsonObject.put("users",new ArrayList<>());
        }
        jsonObject.put("goods",goodsResp);
        return jsonObject;
    }

    @GetMapping("/{skuId}")
    public JSONObject buy(@PathVariable("skuId") Long skuId, @RequestParam(name="couponUrl",required = false) String couponUrl){
        long id = IdGenerator.nextId();
        PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
        promotionCodeReq.setSubUnionId(String.valueOf(id));
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
            XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByOpenIdAndSkuId(Current.getOpenid(),skuId);
            if(null == xiLeWangAssistance){
                xiLeWangOrder.setInitialRatio(this.ratio);
                xiLeWangOrder.setAssistanceId(0L);
            }else{
                xiLeWangOrder.setInitialRatio(xiLeWangAssistance.getInitialRatio());
                xiLeWangOrder.setAssistanceId(xiLeWangAssistance.getId());
            }
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
