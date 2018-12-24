package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangActivity;
import com.wuwei.consumer.wechat.config.FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "provider-wechat",configuration = FeignConfig.class, fallback= XiLeWangActivityService.class, path = "/xilewang/activity")
public interface XiLeWangActivityService {

    @RequestLine("POST /listAll")
    List<XiLeWangActivity> listAll(@RequestBody XiLeWangActivity xiLeWangActivity);

}
