package com.wuwei.consumer.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.consumer.wechat.service.XiLeWangJdOrderService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/order")
public class XiLeWangOrderController {

    @Autowired
    private XiLeWangJdOrderService xiLeWangJdOrderService;

    @GetMapping
    public List<XiLeWangJdOrder> getOrder(){
        return xiLeWangJdOrderService.selectByOpenid(Current.getOpenid());
    }

}
