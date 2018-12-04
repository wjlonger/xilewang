package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.base.wechat.service.XiLeWangAssistanceUserService;
import com.wuwei.provider.wechat.mapper.XiLeWangAssistanceUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("xiLeWangAssistanceUserService")
public class XiLeWangAssistanceUserServiceImpl implements XiLeWangAssistanceUserService {

    @Autowired
    private XiLeWangAssistanceUserMapper xiLeWangAssistanceUserMapper;

    @Override
    public List<XiLeWangAssistanceUser> selectByAssistanceId(Long assistanceId) {
        if(null == assistanceId || 0 == assistanceId){
            return null;
        }
        return xiLeWangAssistanceUserMapper.selectByAssistanceId(assistanceId);
    }

    @Override
    public int insert(XiLeWangAssistanceUser xiLeWangAssistanceUser) {
        if(null == xiLeWangAssistanceUser){
            return 0;
        }
        if(null == xiLeWangAssistanceUser.getId()){
            xiLeWangAssistanceUser.setId(IdGenerator.nextId());
        }
        xiLeWangAssistanceUser.setGmtCreate(new Date());
        xiLeWangAssistanceUser.setGmtModified(new Date());
        return xiLeWangAssistanceUserMapper.insert(xiLeWangAssistanceUser);
    }

}
