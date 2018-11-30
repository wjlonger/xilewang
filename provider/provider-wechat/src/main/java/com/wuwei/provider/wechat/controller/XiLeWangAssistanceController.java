package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.service.XiLeWangAssistanceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/assistance")
public class XiLeWangAssistanceController {

    @Autowired
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @PostMapping
    public int insertSelective(@RequestBody XiLeWangAssistance xiLeWangAssistance){
        return xiLeWangAssistanceService.insertSelective(xiLeWangAssistance);
    }

    @GetMapping("/{id}")
    public XiLeWangAssistance selectByPrimaryKey(@Param("id") Long id){
        return xiLeWangAssistanceService.selectByPrimaryKey(id);
    }

    @PutMapping
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangAssistance xiLeWangAssistance){
        return xiLeWangAssistanceService.updateByPrimaryKeySelective(xiLeWangAssistance);
    }

}
