package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.base.wechat.service.XiLeWangJdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/jdorder")
public class XiLeWangJdOrderController {

    @Autowired
    private XiLeWangJdOrderService xiLeWangJdOrderService;

    @PostMapping
    public int insert(@RequestBody XiLeWangJdOrder xiLeWangJdOrder){
        return xiLeWangJdOrderService.insert(xiLeWangJdOrder);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody XiLeWangJdOrder xiLeWangJdOrder){
        return xiLeWangJdOrderService.insertSelective(xiLeWangJdOrder);
    }

    @GetMapping("/{orderId}")
    public XiLeWangJdOrder selectByPrimaryKey(@PathVariable("orderId") Long orderId){
        return xiLeWangJdOrderService.selectByPrimaryKey(orderId);
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangJdOrder xiLeWangJdOrder){
        return xiLeWangJdOrderService.updateByPrimaryKeySelective(xiLeWangJdOrder);
    }

    @PutMapping
    public int updateByPrimaryKey(@RequestBody XiLeWangJdOrder xiLeWangJdOrder){
        return xiLeWangJdOrderService.updateByPrimaryKey(xiLeWangJdOrder);
    }

    @GetMapping("/selectByOpenid/{openid}")
    public List<XiLeWangJdOrder> selectByOpenid(@PathVariable("openid") String openid){
        return xiLeWangJdOrderService.selectByOpenid(openid);
    }

}
