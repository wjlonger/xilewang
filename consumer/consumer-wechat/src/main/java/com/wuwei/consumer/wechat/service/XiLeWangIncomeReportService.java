package com.wuwei.consumer.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.wechat.hystric.XiLeWangIncomeReportServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-wechat",fallback= XiLeWangIncomeReportServiceHystric.class, path = "/xilewang/incomereport")
public interface XiLeWangIncomeReportService {

    @RequestLine("GET /listXiLeWangIncomeReport/{openid}?pageNo={pageNo}&pageSize={pageSize}&state={state}")
    PageInfo<XiLeWangIncomeReport> listXiLeWangIncomeReport(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                                            @Param("openid") String openid, @Param("state") Integer state);
}
