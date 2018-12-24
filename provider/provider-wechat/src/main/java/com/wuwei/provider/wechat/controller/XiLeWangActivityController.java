package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangActivity;
import com.wuwei.base.wechat.service.XiLeWangActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/activity")
public class XiLeWangActivityController {

    @Autowired
    private XiLeWangActivityService xiLeWangActivityService;

    @PostMapping
    public int insertSelective(@RequestBody XiLeWangActivity xiLeWangActivity){
        return this.xiLeWangActivityService.insertSelective(xiLeWangActivity);
    }

    @GetMapping("/{id}")
    public XiLeWangActivity selectByPrimaryKey(@PathVariable("id") Long id){
        return this.xiLeWangActivityService.selectByPrimaryKey(id);
    }

    @PutMapping
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangActivity xiLeWangActivity){
        return this.xiLeWangActivityService.updateByPrimaryKeySelective(xiLeWangActivity);
    }

    @PostMapping("/listAll")
    public List<XiLeWangActivity> listAll(@RequestBody XiLeWangActivity xiLeWangActivity){
        return this.xiLeWangActivityService.listAll(xiLeWangActivity);
    }

}
