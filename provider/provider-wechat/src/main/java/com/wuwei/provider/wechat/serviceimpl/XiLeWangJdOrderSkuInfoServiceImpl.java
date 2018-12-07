package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import com.wuwei.base.wechat.service.XiLeWangJdOrderSkuInfoService;
import com.wuwei.provider.wechat.mapper.XiLeWangJdOrderSkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xiLeWangJdOrderSkuInfoService")
public class XiLeWangJdOrderSkuInfoServiceImpl implements XiLeWangJdOrderSkuInfoService {

    @Autowired
    private XiLeWangJdOrderSkuInfoMapper xiLeWangJdOrderSkuInfoMapper;

    @Override
    public int insert(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return xiLeWangJdOrderSkuInfoMapper.insert(xiLeWangJdOrderSkuInfo);
    }

    @Override
    public int insertSelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return xiLeWangJdOrderSkuInfoMapper.insertSelective(xiLeWangJdOrderSkuInfo);
    }

    @Override
    public XiLeWangJdOrderSkuInfo selectByPrimaryKey(Long id) {
        return xiLeWangJdOrderSkuInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return xiLeWangJdOrderSkuInfoMapper.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfo);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        return xiLeWangJdOrderSkuInfoMapper.updateByPrimaryKey(xiLeWangJdOrderSkuInfo);
    }
}
