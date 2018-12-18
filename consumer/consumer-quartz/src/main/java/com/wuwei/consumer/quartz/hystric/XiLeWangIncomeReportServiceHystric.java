package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.quartz.service.XiLeWangIncomeReportService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class XiLeWangIncomeReportServiceHystric implements XiLeWangIncomeReportService {

    @Override
    public int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport) {
        return 0;
    }

    @Override
    public List<XiLeWangIncomeReport> selectBySkuInfoId(Long skuInfoId) {
        return null;
    }

    @Override
    public XiLeWangIncomeReport selectByOpenidAndSkuInfoId(String openid, Long skuInfoId) {
        return null;
    }

    @Override
    public XiLeWangIncomeReport selectByPrimaryKey(Long id) {
        return null;
    }

}
