package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.base.wechat.service.XiLeWangHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;


@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/history")
public class XiLeWangHistoryController {

    @Autowired
    private XiLeWangHistoryService xiLeWangHistoryService;

    @GetMapping("/{id}")
    public XiLeWangHistory selectByPrimaryKey(@PathVariable("id") Long id){
        return xiLeWangHistoryService.selectByPrimaryKey(id);
    }

    @PostMapping("/")
    public int insert(@RequestBody XiLeWangHistory xiLeWangHistory){
        return xiLeWangHistoryService.insert(xiLeWangHistory);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        return xiLeWangHistoryService.insertSelective(xiLeWangHistory);
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        return xiLeWangHistoryService.updateByPrimaryKeySelective(xiLeWangHistory);
    }

    @PutMapping("/")
    public int updateByPrimaryKey(@RequestBody XiLeWangHistory xiLeWangHistory){
        return xiLeWangHistoryService.updateByPrimaryKey(xiLeWangHistory);
    }

}
