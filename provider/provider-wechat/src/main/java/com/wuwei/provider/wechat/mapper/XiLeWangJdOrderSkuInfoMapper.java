package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import org.apache.ibatis.annotations.Param;

public interface XiLeWangJdOrderSkuInfoMapper {

    int insert(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    int insertSelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    XiLeWangJdOrderSkuInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    int updateByPrimaryKey(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    XiLeWangJdOrderSkuInfo selectByOrderIdAndSkuIndex(@Param("jdOrderId") Long jdOrderId,@Param("skuIndex") Integer skuIndex);
}