package com.wuwei.base.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;

public interface XiLeWangIncomeReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XiLeWangIncomeReport record);

    int insertSelective(XiLeWangIncomeReport record);

    XiLeWangIncomeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangIncomeReport record);

    int updateByPrimaryKey(XiLeWangIncomeReport record);
}