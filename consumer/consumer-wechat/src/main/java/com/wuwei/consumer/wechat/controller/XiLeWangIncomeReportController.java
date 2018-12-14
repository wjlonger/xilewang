package com.wuwei.consumer.wechat.controller;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.wechat.service.XiLeWangIncomeReportService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/incomereport")
public class XiLeWangIncomeReportController {

    @Autowired
    private XiLeWangIncomeReportService xiLeWangIncomeReportService;

    @GetMapping
    public PageInfo<XiLeWangIncomeReport> xiLeWangIncomeReportPageInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("state") Integer state){
        return xiLeWangIncomeReportService.listXiLeWangIncomeReport(pageNo,pageSize, Current.getOpenid(),state);
    }

}
