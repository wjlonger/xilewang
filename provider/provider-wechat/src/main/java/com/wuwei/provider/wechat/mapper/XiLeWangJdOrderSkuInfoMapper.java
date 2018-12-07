package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;

public interface XiLeWangJdOrderSkuInfoMapper {

    int insert(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    int insertSelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    XiLeWangJdOrderSkuInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    int updateByPrimaryKey(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);
}