package com.wuwei.consumer.wechat.hystric;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.consumer.wechat.service.XiLeWangIncomeReportService;
import org.springframework.context.annotation.Configuration;

/**
 * @author buzai
 */
@Configuration
public class XiLeWangIncomeReportServiceHystric implements XiLeWangIncomeReportService {

    @Override
    public PageInfo<XiLeWangIncomeReport> listXiLeWangIncomeReport(Integer pageNo, Integer pageSize, String openid, Integer state) {
        return null;
    }

    @Override
    public Double totalPending(String openid) {
        return new Double(0);
    }

    @Override
    public Double invitePending(String openid) {
        return new Double(0);
    }

}
