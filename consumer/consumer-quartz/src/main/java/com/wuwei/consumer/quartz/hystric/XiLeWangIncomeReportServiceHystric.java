package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.quartz.service.XiLeWangIncomeReportService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangIncomeReportServiceHystric implements XiLeWangIncomeReportService {

    @Override
    public int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport) {
        return 0;
    }

    @Override
    public XiLeWangIncomeReport selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport) {
        return 0;
    }

    @Override
    public XiLeWangIncomeReport selectByOpenidAndJdOrderId(String openid, Long jdOrderId) {
        return null;
    }
}
