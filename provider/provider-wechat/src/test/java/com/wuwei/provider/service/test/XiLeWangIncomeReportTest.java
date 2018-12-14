package com.wuwei.provider.service.test;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.provider.wechat.ProviderWeChatApplication;
import com.wuwei.provider.wechat.mapper.XiLeWangIncomeReportMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderWeChatApplication.class)
public class XiLeWangIncomeReportTest {

    @Autowired
    private XiLeWangIncomeReportMapper xiLeWangIncomeReportMapper;

    @Test
    public void list(){
        List<XiLeWangIncomeReport> xiLeWangIncomeReports = xiLeWangIncomeReportMapper.listXiLeWangIncomeReport("oVFUM5D0TQvF9sG6yZlVYm0rYaUg",0);
        System.out.println(xiLeWangIncomeReports);
    }

}
