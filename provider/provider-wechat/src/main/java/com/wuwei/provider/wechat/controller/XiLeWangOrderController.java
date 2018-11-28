package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.base.wechat.service.XiLeWangOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/order")
public class XiLeWangOrderController {

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @GetMapping("/{id}")
    public XiLeWangOrder selectByPrimaryKey(@PathVariable("id") Long id) {
        return xiLeWangOrderService.selectByPrimaryKey(id);
    }

    @PostMapping
    public int insert(@RequestBody XiLeWangOrder xiLeWangOrder){
        return xiLeWangOrderService.insert(xiLeWangOrder);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody XiLeWangOrder xiLeWangOrder) {
        return xiLeWangOrderService.insertSelective(xiLeWangOrder);
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangOrder xiLeWangOrder) {
        return xiLeWangOrderService.updateByPrimaryKeySelective(xiLeWangOrder);
    }

    @PutMapping
    public int updateByPrimaryKey(@RequestBody XiLeWangOrder xiLeWangOrder){
        return xiLeWangOrderService.updateByPrimaryKey(xiLeWangOrder);
    }

}
