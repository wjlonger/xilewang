package com.wuwei.provider.quartz.controller;

import com.wuwei.base.quartz.model.XiLeWangQuartz;
import com.wuwei.base.quartz.service.XiLeWangQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/quartz/job")
public class XiLeWangQuartzController {

    @Autowired
    private XiLeWangQuartzService xiLeWangQuartzService;

    @GetMapping("/list")
    public List<XiLeWangQuartz> listQuartz(){
        return xiLeWangQuartzService.listXiLeWangQuartz();
    }


}
