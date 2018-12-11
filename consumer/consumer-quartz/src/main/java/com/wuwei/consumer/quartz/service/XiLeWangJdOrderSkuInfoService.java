package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangJdOrderSkuInfoServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangJdOrderSkuInfoServiceHystric.class, path = "/xilewang/jdorderskuinfo")
public interface XiLeWangJdOrderSkuInfoService{

    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @RequestLine("GET /{id}")
    XiLeWangJdOrderSkuInfo selectByPrimaryKey(@Param("id") Long id);

    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @RequestLine("PUT /")
    int updateByPrimaryKey(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @RequestLine("GET /{jdOrderId}/{skuId}")
    XiLeWangJdOrderSkuInfo selectBySkuIdAndOrderId(@Param("skuId") Long skuId,@Param("jdOrderId") Long jdOrderId);
}
