package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangJdOrderSkuInfoServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangJdOrderSkuInfoServiceHystric.class, path = "/xilewang/jdorderskuinfo")
public interface XiLeWangJdOrderSkuInfoService extends com.wuwei.base.wechat.service.XiLeWangJdOrderSkuInfoService {

    @Override
    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @Override
    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @Override
    @RequestLine("GET /{id}")
    XiLeWangJdOrderSkuInfo selectByPrimaryKey(@PathVariable("id") Long id);

    @Override
    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKey(@RequestBody XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);
}
