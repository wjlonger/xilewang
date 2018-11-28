package com.wuwei.provider.wechat.serviceimpl;


import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.base.wechat.service.XiLeWangOrderService;
import com.wuwei.provider.wechat.mapper.XiLeWangOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xiLeWangOrderService")
public class XiLeWangOrderServiceImpl implements XiLeWangOrderService {

    @Autowired
    private XiLeWangOrderMapper xiLeWangOrderMapper;

    @Override
    public XiLeWangOrder selectByPrimaryKey(Long id) {
        return xiLeWangOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(XiLeWangOrder xiLeWangOrder) {
        return xiLeWangOrderMapper.insert(xiLeWangOrder);
    }

    @Override
    public int insertSelective(XiLeWangOrder xiLeWangOrder) {
        return xiLeWangOrderMapper.insertSelective(xiLeWangOrder);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangOrder xiLeWangOrder) {
        return xiLeWangOrderMapper.updateByPrimaryKeySelective(xiLeWangOrder);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangOrder xiLeWangOrder) {
        return xiLeWangOrderMapper.updateByPrimaryKey(xiLeWangOrder);
    }
}
