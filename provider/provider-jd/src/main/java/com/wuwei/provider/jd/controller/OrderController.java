package com.wuwei.provider.jd.controller;

import com.wuwei.base.jd.service.OrderService;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public UnionOpenOrderQueryResponse query(@RequestBody OrderReq orderReq){
        return orderService.query(orderReq);
    }

}
