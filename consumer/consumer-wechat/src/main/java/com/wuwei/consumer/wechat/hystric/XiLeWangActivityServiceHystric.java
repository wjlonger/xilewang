package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.wechat.model.XiLeWangActivity;
import com.wuwei.consumer.wechat.service.XiLeWangActivityService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class XiLeWangActivityServiceHystric implements XiLeWangActivityService {

    @Override
    public List<XiLeWangActivity> listAll(XiLeWangActivity xiLeWangActivity) {
        return null;
    }

}
