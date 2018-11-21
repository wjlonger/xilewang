package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.base.wechat.service.XiLeWangUserService;
import com.wuwei.provider.wechat.mapper.XiLeWangUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xiLeWangUserService")
public class XiLeWangUserServiceImpl implements XiLeWangUserService {

    @Autowired
    private XiLeWangUserMapper xiLeWangUserMapper;

    @Override
    public XiLeWangUser selectById(Long id) {
        return xiLeWangUserMapper.selectById(id);
    }

    @Override
    public XiLeWangUser insert(XiLeWangUser xiLeWangUser) {
        xiLeWangUser.setId(IdGenerator.nextId());
        xiLeWangUserMapper.insert(xiLeWangUser);
        return xiLeWangUser;
    }

    @Override
    public XiLeWangUser updateById(XiLeWangUser xiLeWangUser) {
        xiLeWangUserMapper.updateById(xiLeWangUser);
        return xiLeWangUser;
    }

    @Override
    public XiLeWangUser save(XiLeWangUser xiLeWangUser) {
        if(null == xiLeWangUser){
            return null;
        }
        if(null == xiLeWangUser.getId() || null == xiLeWangUserMapper.selectById(xiLeWangUser.getId())){
            xiLeWangUser.setId(IdGenerator.nextId());
            xiLeWangUserMapper.insert(xiLeWangUser);
        }else{
            xiLeWangUserMapper.updateById(xiLeWangUser);
        }
        return xiLeWangUser;
    }
}
