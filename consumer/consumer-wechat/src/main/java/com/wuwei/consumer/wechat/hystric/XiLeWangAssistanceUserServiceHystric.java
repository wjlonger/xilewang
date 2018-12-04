package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceUserService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class XiLeWangAssistanceUserServiceHystric implements XiLeWangAssistanceUserService {

    @Override
    public List<XiLeWangAssistanceUser> selectByAssistanceId(Long assistanceId) {
        return null;
    }

    @Override
    public int insert(XiLeWangAssistanceUser xiLeWangAssistanceUser) {
        return 0;
    }

}
