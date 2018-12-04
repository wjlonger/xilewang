package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.base.wechat.service.XiLeWangHistoryService;
import com.wuwei.provider.wechat.mapper.XiLeWangHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("xiLeWangHistoryService")
public class XiLeWangHistoryServiceImpl implements XiLeWangHistoryService {

    @Autowired
    private XiLeWangHistoryMapper xiLeWangHistoryMapper;

    @Override
    public XiLeWangHistory selectByPrimaryKey(Long id) {
        if(null == id || 0 == id){
            return null;
        }
        return xiLeWangHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory){
            return 0;
        }
        if(null == xiLeWangHistory.getId()){
            xiLeWangHistory.setId(IdGenerator.nextId());
        }
        xiLeWangHistory.setGmtCreate(new Date());
        xiLeWangHistory.setGmtModified(new Date());
        return xiLeWangHistoryMapper.insert(xiLeWangHistory);
    }

    @Override
    public int insertSelective(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory){
            return 0;
        }
        if(null == xiLeWangHistory.getId()){
            xiLeWangHistory.setId(IdGenerator.nextId());
        }
        xiLeWangHistory.setGmtCreate(new Date());
        xiLeWangHistory.setGmtModified(new Date());
        return xiLeWangHistoryMapper.insertSelective(xiLeWangHistory);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory || null == xiLeWangHistory.getId()){
            return 0;
        }
        xiLeWangHistory.setGmtModified(new Date());
        return xiLeWangHistoryMapper.updateByPrimaryKeySelective(xiLeWangHistory);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory || null ==  xiLeWangHistory.getId()){
            return 0;
        }
        xiLeWangHistory.setGmtModified(new Date());
        return xiLeWangHistoryMapper.updateByPrimaryKey(xiLeWangHistory);
    }

}
