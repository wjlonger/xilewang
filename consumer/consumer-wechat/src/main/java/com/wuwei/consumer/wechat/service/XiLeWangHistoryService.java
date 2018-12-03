package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.hystric.XiLeWangHistoryServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangHistoryServiceHystric.class, path = "/xilewang/history")
public interface XiLeWangHistoryService extends com.wuwei.base.wechat.service.XiLeWangHistoryService {

    @Override
    @RequestLine("GET /{id}")
    XiLeWangHistory selectByPrimaryKey(@Param("id") final Long id);

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
