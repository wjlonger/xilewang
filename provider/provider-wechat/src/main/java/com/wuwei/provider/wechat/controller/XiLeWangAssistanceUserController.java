package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.base.wechat.service.XiLeWangAssistanceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/assistance/user")
public class XiLeWangAssistanceUserController {

    @Autowired
    private XiLeWangAssistanceUserService xiLeWangAssistanceUserService;

    @GetMapping("/{assistanceId}")
    public List<XiLeWangAssistanceUser> selectByAssistanceId(@PathVariable("assistanceId") Long assistanceId){
        return xiLeWangAssistanceUserService.selectByAssistanceId(assistanceId);
    }

    @PostMapping
    public int insert(@RequestBody XiLeWangAssistanceUser xiLeWangAssistanceUser){
        return xiLeWangAssistanceUserService.insert(xiLeWangAssistanceUser);
    }

}
