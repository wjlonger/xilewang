package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/assistance")
public class XiLeWangAssistanceController {

    @Autowired
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @Value("${goods.ratio}")
    private BigDecimal ratio;

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/{skuId}")
    public JSONObject getAssistanceId(@PathVariable Long skuId) {
        String openId = Current.getOpenid();
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByOpenIdAndSkuId(openId,skuId);
        if(null == xiLeWangAssistance){
            xiLeWangAssistance = new XiLeWangAssistance();
            final long id = IdGenerator.nextId();
            xiLeWangAssistance.setId(id);
            xiLeWangAssistance.setOpenid(openId);
            xiLeWangAssistance.setSkuId(skuId);
            xiLeWangAssistance.setInitialRatio(ratio);
            int i = xiLeWangAssistanceService.insertSelective(xiLeWangAssistance);
            if(i <= 0){
                jsonObject.put("code",0);
            }else{
                jsonObject.put("code",1);
                jsonObject.put("assistance",id);
            }
        }else{
            jsonObject.put("code",1);
            jsonObject.put("assistance",xiLeWangAssistance.getId());
        }
        return jsonObject;
    }

    @PutMapping
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangAssistance xiLeWangAssistance) {
        return xiLeWangAssistanceService.updateByPrimaryKeySelective(xiLeWangAssistance);
    }

}
