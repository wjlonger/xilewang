package com.wuwei.consumer.quartz.service;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.quartz.hystric.XiLeWangIncomeReportServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "provider-wechat",fallback= XiLeWangIncomeReportServiceHystric.class, path = "/xilewang/incomereport")
public interface XiLeWangIncomeReportService {

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport);

    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport);

    @RequestLine("GET /selectBySkuInfoId/{skuInfoId}")
    List<XiLeWangIncomeReport> selectBySkuInfoId(@Param("skuInfoId") Long skuInfoId);

    @RequestLine("GET /selectByOpenidAndSkuInfoId/{openid}/{skuInfoId}")
    XiLeWangIncomeReport selectByOpenidAndSkuInfoId(@Param("openid") String openid, @Param("skuInfoId") Long skuInfoId);
}
