package com.wuwei.provider.wechat.serviceimpl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.base.wechat.service.XiLeWangHistoryService;
import com.wuwei.provider.wechat.mapper.XiLeWangHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xiLeWangHistoryService")
public class XiLeWangHistoryImpl implements XiLeWangHistoryService {

    @Autowired
    private XiLeWangHistoryMapper xiLeWangHistoryMapper;

    @Override
    public XiLeWangHistory selectByPrimaryKey(Long id) {
        if(null == id){
            return null;
        }
        return xiLeWangHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory){
            return 0;
        }
        return xiLeWangHistoryMapper.insert(xiLeWangHistory);
    }

    @Override
    public int insertSelective(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory){
            return 0;
        }
        return xiLeWangHistoryMapper.insertSelective(xiLeWangHistory);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory){
            return 0;
        }
        return xiLeWangHistoryMapper.updateByPrimaryKeySelective(xiLeWangHistory);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangHistory xiLeWangHistory) {
        if(null == xiLeWangHistory){
            return 0;
        }
        return xiLeWangHistoryMapper.updateByPrimaryKey(xiLeWangHistory);
    }

}
