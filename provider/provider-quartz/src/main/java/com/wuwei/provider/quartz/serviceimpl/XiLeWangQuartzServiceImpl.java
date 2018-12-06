package com.wuwei.provider.quartz.serviceimpl;

import com.wuwei.base.quartz.model.XiLeWangQuartz;
import com.wuwei.base.quartz.service.XiLeWangQuartzService;
import com.wuwei.provider.quartz.mapper.XiLeWangQuartzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("xiLeWangQuartzService")
public class XiLeWangQuartzServiceImpl implements XiLeWangQuartzService {

    @Autowired
    private XiLeWangQuartzMapper xiLeWangQuartzMapper;

    @Override
    public List<XiLeWangQuartz> listQuartz() {
        return xiLeWangQuartzMapper.listQuartz();
    }

}
