package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.quartz.model.XiLeWangQuartz;
import com.wuwei.consumer.quartz.service.XiLeWangQuartzService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class XiLeWangQuartzServiceHystric implements XiLeWangQuartzService {

    @Override
    public List<XiLeWangQuartz> listQuartz() {
        return null;
    }

}
