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

    @RequestLine("GET /?type={type}&openid={openid}&jdOrderId={jdOrderId}&jdOrderSkuIndex={jdOrderSkuIndex}")
    XiLeWangIncomeReport selectByProperty(@Param("type") Integer type, @Param("openid") String openid,
                                          @Param("jdOrderId") Long jdOrderId, @Param("jdOrderSkuIndex") Integer jdOrderSkuIndex);

    @RequestLine("GET /selectByJdOrderId/{jdOrderId}")
    List<XiLeWangIncomeReport> selectByJdOrderId(@Param("jdOrderId") Long jdOrderId);
}
