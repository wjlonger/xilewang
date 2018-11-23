package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangHistoryHystric implements XiLeWangHistoryService {

    @Override
    public XiLeWangHistory selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int insert(XiLeWangHistory xiLeWangHistory) {
        return 0;
    }

    @Override
    public int insertSelective(XiLeWangHistory xiLeWangHistory) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(XiLeWangHistory xiLeWangHistory) {
        return 0;
    }

}
