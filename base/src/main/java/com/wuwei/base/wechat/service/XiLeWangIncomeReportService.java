package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;

import java.util.List;

public interface XiLeWangIncomeReportService {

    int insert(XiLeWangIncomeReport xiLeWangIncomeReport);

    int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    int updateByPrimaryKey(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByProperty(Integer type,String openid, Long jdOrderId,Integer jdOrderSkuIndex);

    List<XiLeWangIncomeReport> selectByJdOrderId(Long jdOrderId);

}
