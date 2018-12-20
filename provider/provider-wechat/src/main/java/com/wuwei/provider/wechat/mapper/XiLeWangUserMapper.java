package com.wuwei.provider.wechat.mapper;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangUser;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface XiLeWangUserMapper {

    int insert(XiLeWangUser xiLeWangUser);

    int insertSelective(XiLeWangUser xiLeWangUser);

    XiLeWangUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(XiLeWangUser xiLeWangUser);

    int updateByPrimaryKey(XiLeWangUser xiLeWangUser);

    int updateRebateMoneyByPrimaryKey(@Param("modifyMoney")BigDecimal modifyMoney, @Param("openid") String openid, @Param("gmtModified")Date gmtModified);

    int updateAssistanceMoneyByPrimaryKey(@Param("modifyMoney")BigDecimal modifyMoney, @Param("openid") String openid, @Param("gmtModified")Date gmtModified);

    int updateMasterMoneyByPrimaryKey(@Param("modifyMoney")BigDecimal modifyMoney, @Param("openid") String openid, @Param("gmtModified")Date gmtModified);

    int updateMoneyByPrimaryKey(@Param("modifyMoney")BigDecimal modifyMoney, @Param("openid") String openid, @Param("gmtModified")Date gmtModified);

    int inviteCount(@Param("openid") String openid);

    List<PageInfo> listByMasterOpenid(@Param("openid") String openid);
}