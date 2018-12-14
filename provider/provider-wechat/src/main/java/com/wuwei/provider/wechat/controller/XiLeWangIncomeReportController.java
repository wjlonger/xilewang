package com.wuwei.provider.wechat.controller;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.base.wechat.service.XiLeWangIncomeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public XiLeWangIncomeReport selectByProperty(@RequestParam("type") Integer type, @RequestParam("openid") String openid,
                                                 @RequestParam("jdOrderId") Long jdOrderId, @RequestParam("jdOrderSkuIndex") Integer jdOrderSkuIndex){
        return xiLeWangIncomeReportService.selectByProperty(type, openid, jdOrderId, jdOrderSkuIndex);
    }

    @GetMapping("/selectByProperty/{jdOrderId}")
    public List<XiLeWangIncomeReport> selectByJdOrderId(@PathVariable("jdOrderId") Long jdOrderId){
        return xiLeWangIncomeReportService.selectByJdOrderId(jdOrderId);
    }

    @GetMapping("/listXiLeWangIncomeReport/{openid}")
    public PageInfo<XiLeWangIncomeReport> listXiLeWangIncomeReport(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                                                   @PathVariable("openid") String openid, @RequestParam("state") Integer state){
        return xiLeWangIncomeReportService.listXiLeWangIncomeReport(pageNo, pageSize, openid, state);
    }

}
