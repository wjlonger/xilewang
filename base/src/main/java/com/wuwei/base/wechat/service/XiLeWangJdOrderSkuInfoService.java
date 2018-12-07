package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;

public interface XiLeWangJdOrderSkuInfoService {

    int insert(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    int insertSelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    XiLeWangJdOrderSkuInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

    int updateByPrimaryKey(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo);

}
