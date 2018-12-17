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

    @GetMapping("/listXiLeWangIncomeReport/{openid}")
    public PageInfo<XiLeWangIncomeReport> listXiLeWangIncomeReport(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                                                   @PathVariable("openid") String openid, @RequestParam("state") Integer state){
        return xiLeWangIncomeReportService.listXiLeWangIncomeReport(pageNo, pageSize, openid, state);
    }

    @GetMapping("/selectBySkuInfoId/{skuInfoId}")
    public List<XiLeWangIncomeReport> selectBySkuInfoId(@PathVariable("skuInfoId") Long skuInfoId){
        return xiLeWangIncomeReportService.selectBySkuInfoId(skuInfoId);
    }

    @GetMapping("/selectByOpenidAndSkuInfoId/{openid}/{skuInfoId}")
    public XiLeWangIncomeReport selectByOpenidAndSkuInfoId(@PathVariable("openid") String openid,@PathVariable("skuInfoId") Long skuInfoId){
        return xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(openid,skuInfoId);
    }

}
