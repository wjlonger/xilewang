package com.wuwei.consumer.quartz.service;


import com.wuwei.base.quartz.model.XiLeWangQuartz;
import com.wuwei.consumer.quartz.config.FeignConfig;
import com.wuwei.consumer.quartz.hystric.XiLeWangQuartzServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "provider-quartz",configuration = FeignConfig.class, fallback= XiLeWangQuartzServiceHystric.class, path = "/quartz/job")
public interface XiLeWangQuartzService{

    @RequestLine("GET /list")
    List<XiLeWangQuartz> listQuartz();

}
