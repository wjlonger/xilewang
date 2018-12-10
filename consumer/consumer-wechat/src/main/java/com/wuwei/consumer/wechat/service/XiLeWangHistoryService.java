package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.hystric.XiLeWangHistoryServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangHistoryServiceHystric.class, path = "/xilewang/history")
public interface XiLeWangHistoryService{

    @RequestLine("GET /{id}")
    XiLeWangHistory selectByPrimaryKey(@Param("id") final Long id);

    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangHistory xiLeWangHistory);

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory);

    @RequestLine("PUT /updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory);

    @RequestLine("PUT /")
    int updateByPrimaryKey(@RequestBody XiLeWangHistory xiLeWangHistory);

}
