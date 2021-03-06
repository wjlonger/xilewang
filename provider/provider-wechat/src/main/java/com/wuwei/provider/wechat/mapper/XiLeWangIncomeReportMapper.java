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

    List<XiLeWangIncomeReport> listXiLeWangIncomeReport(@Param("openid") String openid, @Param("state") Integer state);

    List<XiLeWangIncomeReport> selectBySkuInfoId(@Param("skuInfoId") Long skuInfoId);

    XiLeWangIncomeReport selectByOpenidAndSkuInfoId(@Param("openid") String openid, @Param("skuInfoId") Long skuInfoId);

    double totalPending(@Param("openid")String openid);

    double invitPending(@Param("openid") String openid);

    double invitTotal(@Param("openid") String openid);

}