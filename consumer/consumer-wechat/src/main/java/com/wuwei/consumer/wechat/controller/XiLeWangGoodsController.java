package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceService;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import com.wuwei.consumer.wechat.service.XiLeWangPromotionService;
import com.wuwei.consumer.wechat.utils.Current;
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
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @Value("${goods.ratio}")
    private BigDecimal ratio;

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/{skuId}")
    public JSONObject buy(@PathVariable("skuId") long skuId, @RequestParam(name="couponUrl",required = false) String couponUrl){
        long id = IdGenerator.nextId();
        PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
        promotionCodeReq.setSubUnionId(String.valueOf(id));
        promotionCodeReq.setMaterialId(String.format("https://wqitem.jd.com/item/view?sku=%d",skuId));
        promotionCodeReq.setCouponUrl(couponUrl);
        String url = xiLeWangPromotionService.getBySubUnionId(promotionCodeReq);
        if(!StringUtils.isEmpty(url)){
            XiLeWangOrder xiLeWangOrder = new XiLeWangOrder();
            xiLeWangOrder.setId(id);
            xiLeWangOrder.setSkuId(skuId);
            xiLeWangOrder.setOpenid(Current.getOpenid());
            xiLeWangOrder.setUrl(url);
            XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByOpenIdAndSkuId(Current.getOpenid(),skuId);
            if(null == xiLeWangAssistance){
                xiLeWangOrder.setInitialRatio(ratio);
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
