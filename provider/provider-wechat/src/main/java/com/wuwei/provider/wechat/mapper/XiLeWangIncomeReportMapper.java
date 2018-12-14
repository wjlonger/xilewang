package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XiLeWangIncomeReportMapper {

    int insert(XiLeWangIncomeReport xiLeWangIncomeReport);

    int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    int updateByPrimaryKey(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByProperty(@Param("type") Integer type, @Param("openid") String openid, @Param("jdOrderId") Long jdOrderId,@Param("jdOrderSkuIndex") Integer jdOrderSkuIndex);

    List<XiLeWangIncomeReport> selectByJdOrderId(@Param("jdOrderId") Long jdOrderId);

    List<XiLeWangIncomeReport> listXiLeWangIncomeReport(@Param("openid") String openid, @Param("state") Integer state);

}