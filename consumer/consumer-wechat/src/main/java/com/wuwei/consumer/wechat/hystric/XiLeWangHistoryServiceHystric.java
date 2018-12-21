package com.wuwei.consumer.wechat.hystric;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangHistoryServiceHystric implements XiLeWangHistoryService {

    @Override
    public int insertSelective(XiLeWangHistory xiLeWangHistory) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory) {
        return 0;
    }

    @Override
    public PageInfo<XiLeWangHistory> selectByOpenid(String openid, Integer pageNo, Integer pageSize) {
        return null;
    }


}
