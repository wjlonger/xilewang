package com.wuwei.provider.wechat.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.util.IdGenerator;
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

    @Override
    public PageInfo<XiLeWangHistory> selectByOpenid(String openid, Integer pageNo, Integer pageSize) {
        PageInfo<XiLeWangHistory> xiLeWangHistoryPageInfo = PageHelper.startPage(pageNo,pageSize).setOrderBy("gmt_create desc")
                .doSelectPageInfo(() -> this.xiLeWangHistoryMapper.selectByOpenid(openid));
        return xiLeWangHistoryPageInfo;
    }

}
