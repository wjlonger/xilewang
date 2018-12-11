package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.wechat.hystric.XiLeWangIncomeReportServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangIncomeReportServiceHystric.class, path = "/xilewang/incomereport")
public interface XiLeWangIncomeReportService {

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport);

    @RequestLine("GET /{id}")
    XiLeWangIncomeReport selectByPrimaryKey(@Param("id") Long id);

    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangIncomeReport xiLeWangIncomeReport);

    @GetMapping("/{openid}/{jdOrderId}")
    XiLeWangIncomeReport selectByOpenidAndJdOrderId(@Param("openid") String openid, @Param("jdOrderId") Long jdOrderId);

}
