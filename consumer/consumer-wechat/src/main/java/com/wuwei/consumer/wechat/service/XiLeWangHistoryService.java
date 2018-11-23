package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

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
