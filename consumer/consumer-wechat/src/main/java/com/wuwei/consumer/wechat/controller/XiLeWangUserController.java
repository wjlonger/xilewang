package com.wuwei.consumer.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/user")
public class XiLeWangUserController {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @GetMapping("/{id}")
    public XiLeWangUser selectById(@PathVariable("id") Long id){
        return xiLeWangUserService.selectById(id);
    }

    @PostMapping
    public XiLeWangUser insert(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.insert(xiLeWangUser);
    }

    @PutMapping
    public XiLeWangUser updateById(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.updateById(xiLeWangUser);
    }

    @PostMapping("/save")
    public XiLeWangUser save(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.save(xiLeWangUser);
    }

}
