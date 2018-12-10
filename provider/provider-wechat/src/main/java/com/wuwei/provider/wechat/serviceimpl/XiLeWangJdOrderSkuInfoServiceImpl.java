package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;
import com.wuwei.base.wechat.service.XiLeWangJdOrderSkuInfoService;
import com.wuwei.provider.wechat.mapper.XiLeWangJdOrderSkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("xiLeWangJdOrderSkuInfoService")
public class XiLeWangJdOrderSkuInfoServiceImpl implements XiLeWangJdOrderSkuInfoService {

    @Autowired
    private XiLeWangJdOrderSkuInfoMapper xiLeWangJdOrderSkuInfoMapper;

    @Override
    public int insert(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        if(null == xiLeWangJdOrderSkuInfo){
            return 0;
        }
        xiLeWangJdOrderSkuInfo.setGmtCreate(new Date());
        xiLeWangJdOrderSkuInfo.setGmtModified(new Date());
        return xiLeWangJdOrderSkuInfoMapper.insert(xiLeWangJdOrderSkuInfo);
    }

    @Override
    public int insertSelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        if(null == xiLeWangJdOrderSkuInfo){
            return 0;
        }
        xiLeWangJdOrderSkuInfo.setGmtCreate(new Date());
        xiLeWangJdOrderSkuInfo.setGmtModified(new Date());
        return xiLeWangJdOrderSkuInfoMapper.insertSelective(xiLeWangJdOrderSkuInfo);
    }

    @Override
    public XiLeWangJdOrderSkuInfo selectByPrimaryKey(Long id) {
        if(null == id || 0 == id){
            return null;
        }
        return xiLeWangJdOrderSkuInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        if(null == xiLeWangJdOrderSkuInfo){
            return 0;
        }
        xiLeWangJdOrderSkuInfo.setGmtModified(new Date());
        return xiLeWangJdOrderSkuInfoMapper.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfo);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo) {
        if(null == xiLeWangJdOrderSkuInfo){
            return 0;
        }
        xiLeWangJdOrderSkuInfo.setGmtModified(new Date());
        return xiLeWangJdOrderSkuInfoMapper.updateByPrimaryKey(xiLeWangJdOrderSkuInfo);
    }
}
