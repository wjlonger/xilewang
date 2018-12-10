package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;

public interface XiLeWangIncomeReportService {

    int insert(XiLeWangIncomeReport xiLeWangIncomeReport);

    int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    int updateByPrimaryKey(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByOpenidAndJdOrderId(String openid, Long jdOrderId);

}
