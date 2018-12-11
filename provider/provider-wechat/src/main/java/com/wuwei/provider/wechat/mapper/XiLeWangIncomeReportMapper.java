package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import org.apache.ibatis.annotations.Param;

public interface XiLeWangIncomeReportMapper {

    int insert(XiLeWangIncomeReport xiLeWangIncomeReport);

    int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport);

    int updateByPrimaryKey(XiLeWangIncomeReport xiLeWangIncomeReport);

    XiLeWangIncomeReport selectByOpenidAndJdOrderIdAndSkuId(@Param("openid") String openid, @Param("jdOrderId") Long jdOrderId,@Param("skuId") Long skuId);

}