package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.base.wechat.service.XiLeWangIncomeReportService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/incomereport")
public class XiLeWangIncomeReportController {

    @Autowired
    private XiLeWangIncomeReportService xiLeWangIncomeReportService;

    @PostMapping
    public int insert(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport){
        return xiLeWangIncomeReportService.insert(xiLeWangIncomeReport);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport){
        return xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
    }

    @GetMapping("/{id}")
    public XiLeWangIncomeReport selectByPrimaryKey(@PathVariable("id") Long id){
        return xiLeWangIncomeReportService.selectByPrimaryKey(id);
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport){
        return xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
    }

    @PutMapping
    public int updateByPrimaryKey(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport){
        return xiLeWangIncomeReportService.updateByPrimaryKey(xiLeWangIncomeReport);
    }

    @GetMapping("/{openid}/{jdOrderId}")
    public XiLeWangIncomeReport selectByOpenidAndJdOrderId(@PathVariable("openid") String openid, @PathVariable("jdOrderId") Long jdOrderId){
        return xiLeWangIncomeReportService.selectByOpenidAndJdOrderId(openid, jdOrderId);
    }

}
