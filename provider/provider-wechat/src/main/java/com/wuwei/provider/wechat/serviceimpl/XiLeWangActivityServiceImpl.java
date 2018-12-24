package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.wechat.model.XiLeWangActivity;
import com.wuwei.base.wechat.service.XiLeWangActivityService;
import com.wuwei.provider.wechat.mapper.XiLeWangActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("xiLeWangActivityService")
public class XiLeWangActivityServiceImpl implements XiLeWangActivityService {

    @Autowired
    private XiLeWangActivityMapper xiLeWangActivityMapper;

    @Override
    public int insertSelective(XiLeWangActivity xiLeWangActivity) {
        if(null == xiLeWangActivity){
            return 0;
        }
        xiLeWangActivity.setGmtCreate(new Date());
        xiLeWangActivity.setGmtModified(new Date());
        return this.xiLeWangActivityMapper.insertSelective(xiLeWangActivity);
    }

    @Override
    public XiLeWangActivity selectByPrimaryKey(Long id) {
        if(null == id || id == 0){
            return null;
        }
        return this.xiLeWangActivityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangActivity xiLeWangActivity) {
        if(null == xiLeWangActivity){
            return 0;
        }
        xiLeWangActivity.setGmtModified(new Date());
        return this.xiLeWangActivityMapper.updateByPrimaryKeySelective(xiLeWangActivity);
    }

    @Override
    public List<XiLeWangActivity> listAll(XiLeWangActivity xiLeWangActivity) {
        if(null == xiLeWangActivity){
            xiLeWangActivity = new XiLeWangActivity();
        }
        return this.xiLeWangActivityMapper.listAll(xiLeWangActivity);
    }
}
