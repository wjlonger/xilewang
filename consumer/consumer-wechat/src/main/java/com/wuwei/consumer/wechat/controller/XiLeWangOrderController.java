package com.wuwei.consumer.wechat.controller;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.vo.XiLeWangJdOrderVo;
import com.wuwei.consumer.wechat.service.XiLeWangJdOrderService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/order")
public class XiLeWangOrderController {

    @Autowired
    private XiLeWangJdOrderService xiLeWangJdOrderService;

    @GetMapping
    public PageInfo<XiLeWangJdOrderVo> getOrder(@RequestParam("pageNo") Integer pageNo,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam(value = "validCode",required = false) Integer validCode){
        return xiLeWangJdOrderService.listByOpenidAndViladCode(pageNo,pageSize,Current.getOpenid(),validCode);
    }

}
