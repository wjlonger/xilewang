package com.wuwei.base.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;

import java.util.List;

public interface XiLeWangIncomeReportService {

    int insert(XiLeWangIncomeReport xiLeWangIncomeReport);

    int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    int updateByPrimaryKey(XiLeWangIncomeReport xiLeWangIncomeReport);

    PageInfo<XiLeWangIncomeReport> listXiLeWangIncomeReport(Integer pageNo, Integer pageSize, String openid, Integer state);

    List<XiLeWangIncomeReport> selectBySkuInfoId(Long skuInfoId);

    XiLeWangIncomeReport selectByOpenidAndSkuInfoId(String openid, Long skuInfoId);

}
