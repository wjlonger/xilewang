package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.base.wechat.service.XiLeWangJdOrderService;
import com.wuwei.provider.wechat.mapper.XiLeWangJdOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xiLeWangJdOrderService")
public class XiLeWangJdOrderServiceImpl implements XiLeWangJdOrderService {

    @Autowired
    private XiLeWangJdOrderMapper xiLeWangJdOrderMapper;

    @Override
    public int insert(XiLeWangJdOrder xiLeWangJdOrder) {
        return xiLeWangJdOrderMapper.insert(xiLeWangJdOrder);
    }

    @Override
    public int insertSelective(XiLeWangJdOrder xiLeWangJdOrder) {
        return xiLeWangJdOrderMapper.insertSelective(xiLeWangJdOrder);
    }

    @Override
    public XiLeWangJdOrder selectByPrimaryKey(Long orderId) {
        return xiLeWangJdOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangJdOrder xiLeWangJdOrder) {
        return xiLeWangJdOrderMapper.updateByPrimaryKeySelective(xiLeWangJdOrder);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangJdOrder xiLeWangJdOrder) {
        return xiLeWangJdOrderMapper.updateByPrimaryKey(xiLeWangJdOrder);
    }
}
