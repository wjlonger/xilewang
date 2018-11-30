package com.wuwei.provider.wechat.serviceimpl;


import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.base.wechat.service.XiLeWangOrderService;
import com.wuwei.provider.wechat.mapper.XiLeWangOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("xiLeWangOrderService")
public class XiLeWangOrderServiceImpl implements XiLeWangOrderService {

    @Autowired
    private XiLeWangOrderMapper xiLeWangOrderMapper;

    @Override
    public XiLeWangOrder selectByPrimaryKey(Long id) {
        if(null == id){
            return null;
        }
        return xiLeWangOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(XiLeWangOrder xiLeWangOrder) {
        if(null == xiLeWangOrder){
            return 0;
        }
        xiLeWangOrder.setGmtCreate(new Date());
        xiLeWangOrder.setGmtModified(new Date());
        return xiLeWangOrderMapper.insert(xiLeWangOrder);
    }

    @Override
    public int insertSelective(XiLeWangOrder xiLeWangOrder) {
        if(null == xiLeWangOrder){
            return 0;
        }
        xiLeWangOrder.setGmtCreate(new Date());
        xiLeWangOrder.setGmtModified(new Date());
        return xiLeWangOrderMapper.insertSelective(xiLeWangOrder);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangOrder xiLeWangOrder) {
        if(null == xiLeWangOrder){
            return 0;
        }
        xiLeWangOrder.setGmtModified(new Date());
        return xiLeWangOrderMapper.updateByPrimaryKeySelective(xiLeWangOrder);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangOrder xiLeWangOrder) {
        if(null == xiLeWangOrder){
            return 0;
        }
        xiLeWangOrder.setGmtModified(new Date());
        return xiLeWangOrderMapper.updateByPrimaryKey(xiLeWangOrder);
    }
}
