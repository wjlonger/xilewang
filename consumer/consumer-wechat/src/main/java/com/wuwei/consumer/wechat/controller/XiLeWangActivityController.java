package com.wuwei.consumer.wechat.controller;


import com.wuwei.base.wechat.model.XiLeWangActivity;
import com.wuwei.consumer.wechat.service.XiLeWangActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/activity")
public class XiLeWangActivityController {

    @Autowired
    private XiLeWangActivityService xiLeWangActivityService;

    @GetMapping
    public List<XiLeWangActivity> listAll(){
        XiLeWangActivity xiLeWangActivity = new XiLeWangActivity();
        xiLeWangActivity.setDeleted(false);
        return this.xiLeWangActivityService.listAll(xiLeWangActivity);
    }

}
