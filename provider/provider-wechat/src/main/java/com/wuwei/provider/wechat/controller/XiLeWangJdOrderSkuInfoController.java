package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import com.wuwei.base.wechat.service.XiLeWangJdOrderSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/jdorderskuinfo")
public class XiLeWangJdOrderSkuInfoController {

    @Autowired
    private XiLeWangJdOrderSkuInfoService xiLeWangJdOrderSkuInfoService;

    @PostMapping
    public int insert(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo){
        return xiLeWangJdOrderSkuInfoService.insert(xiLeWangJdOrderSkuInfo);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo){
        return xiLeWangJdOrderSkuInfoService.insertSelective(xiLeWangJdOrderSkuInfo);
    }

    @GetMapping("/{id}")
    public XiLeWangJdOrderSkuInfo selectByPrimaryKey(@PathVariable("id") Long id){
        return xiLeWangJdOrderSkuInfoService.selectByPrimaryKey(id);
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo){
        return xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfo);
    }

    @PutMapping
    public int updateByPrimaryKey(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo){
        return xiLeWangJdOrderSkuInfoService.updateByPrimaryKey(xiLeWangJdOrderSkuInfo);
    }

    @GetMapping("/{jdOrderId}/{skuId}")
    public XiLeWangJdOrderSkuInfo selectBySkuIdAndOrderId(@PathVariable("jdOrderId") Long jdOrderId, @PathVariable("skuId") Long skuId){
        return xiLeWangJdOrderSkuInfoService.selectBySkuIdAndOrderId(skuId,jdOrderId);
    }
}
