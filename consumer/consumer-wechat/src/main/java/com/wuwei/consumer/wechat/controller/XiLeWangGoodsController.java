package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.jd.model.PromotionSearch;
import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangPromotionService;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/goods")
public class XiLeWangGoodsController {

    @Value("${goods.ratio}")
    private double raido;

    private JSONObject jsonObject = new JSONObject();

    @Autowired
    private XiLeWangPromotionService xiLeWangPromotionService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/{skuId}")
    public JSONObject buy(@PathVariable("skuId") long skuId){

        long id = IdGenerator.nextId();

        PromotionSearch promotionSearch = new PromotionSearch();
        promotionSearch.setSubUnionId(String.valueOf(id));
        promotionSearch.setMaterialId(String.format("https://wqitem.jd.com/item/view?sku=%d",skuId));
        String url = xiLeWangPromotionService.GetBySubUnionId(promotionSearch);

        XiLeWangOrder xiLeWangOrder = new XiLeWangOrder();
        xiLeWangOrder.setId(id);
        xiLeWangOrder.setSkuId(skuId);
        xiLeWangOrder.setOpenid(Current.getOpenid());
        xiLeWangOrder.setUrl(url);
        amqpTemplate.convertAndSend("xilewang_order_insert",xiLeWangOrder);

        jsonObject.put("code",1);
        jsonObject.put("url",url);
        return jsonObject;
    }

}
