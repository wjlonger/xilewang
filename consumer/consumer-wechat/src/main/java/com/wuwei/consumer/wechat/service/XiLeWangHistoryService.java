package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.hystric.XiLeWangUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangUserServiceHystric.class, path = "/xilewang/user")
public interface XiLeWangHistoryService extends com.wuwei.base.wechat.service.XiLeWangHistoryService {

    @Override
    @RequestLine("GET /{id}")
    XiLeWangHistory selectByPrimaryKey(@Param("id") Long id);

    @Override
    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangHistory xiLeWangHistory);

    @Override
    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory);

    @Override
    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory);

    @Override
    @RequestLine("PUT /")
    int updateByPrimaryKey(@RequestBody XiLeWangHistory xiLeWangHistory);

}
