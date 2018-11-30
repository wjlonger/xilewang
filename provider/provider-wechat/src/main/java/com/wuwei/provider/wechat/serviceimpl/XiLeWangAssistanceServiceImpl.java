package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.service.XiLeWangAssistanceService;
import com.wuwei.provider.wechat.mapper.XiLeWangAssistanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("xiLeWangAssistanceService")
public class XiLeWangAssistanceServiceImpl implements XiLeWangAssistanceService {

    @Autowired
    private XiLeWangAssistanceMapper xiLeWangAssistanceMapper;

    @Override
    public int insertSelective(XiLeWangAssistance xiLeWangAssistance) {
        if(null == xiLeWangAssistance){
            return 0;
        }
        if(null == xiLeWangAssistance.getId()){
            xiLeWangAssistance.setId(IdGenerator.nextId());
        }
        xiLeWangAssistance.setGmtCreate(new Date());
        xiLeWangAssistance.setGmtModified(new Date());
        return xiLeWangAssistanceMapper.insertSelective(xiLeWangAssistance);
    }

    @Override
    public XiLeWangAssistance selectByPrimaryKey(Long id) {
        if(null == id){
            return null;
        }
        return xiLeWangAssistanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance) {
        if(null == xiLeWangAssistance || null ==  xiLeWangAssistance.getId()){
            return 0;
        }
        xiLeWangAssistance.setGmtModified(new Date());
        return xiLeWangAssistanceMapper.updateByPrimaryKeySelective(xiLeWangAssistance);
    }
}
