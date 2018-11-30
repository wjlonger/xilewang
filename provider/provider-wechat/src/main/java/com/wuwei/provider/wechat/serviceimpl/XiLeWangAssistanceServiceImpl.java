package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.service.XiLeWangAssistanceService;
import com.wuwei.provider.wechat.mapper.XiLeWangAssistanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xiLeWangAssistanceService")
public class XiLeWangAssistanceServiceImpl implements XiLeWangAssistanceService {

    @Autowired
    private XiLeWangAssistanceMapper xiLeWangAssistanceMapper;

    @Override
    public int insertSelective(XiLeWangAssistance xiLeWangAssistance) {
        return xiLeWangAssistanceMapper.insertSelective(xiLeWangAssistance);
    }

    @Override
    public XiLeWangAssistance selectByPrimaryKey(Long id) {
        return xiLeWangAssistanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance) {
        return xiLeWangAssistanceMapper.updateByPrimaryKeySelective(xiLeWangAssistance);
    }
}
