package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import com.wuwei.consumer.quartz.service.XiLeWangJdOrderSkuInfoService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangJdOrderSkuInfoServiceHystric implements XiLeWangJdOrderSkuInfoService {

    @Override
    public int insert(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return 0;
    }

    @Override
    public int insertSelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return 0;
    }

    @Override
    public XiLeWangJdOrderSkuInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return 0;
    }

    @Override
    public XiLeWangJdOrderSkuInfo selectByOrderIdAndSkuIndex(Long jdOrderId, Integer skuIndex) {
        return null;
    }
}
