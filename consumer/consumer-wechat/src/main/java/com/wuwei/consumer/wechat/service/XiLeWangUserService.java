package com.wuwei.consumer.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.hystric.XiLeWangUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangUserServiceHystric.class, path = "/xilewang/user")
public interface XiLeWangUserService extends com.wuwei.base.wechat.service.XiLeWangUserService{

    @Override
    @RequestLine("GET /code2Session/{code}")
    String code2Session(@Param("code") String code);

    @Override
    @RequestLine("GET /{openid}")
    XiLeWangUser selectByOpenid(@Param("openid") String openid);

    @Override
    @RequestLine("POST /")
    int insert(@RequestBody XiLeWangUser xiLeWangUser);

    @Override
    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangUser xiLeWangUser);

    @Override
    @RequestLine("PUT /")
    int updateByOpenid(@RequestBody XiLeWangUser xiLeWangUser);

    @Override
    @RequestLine("POST /save")
    int save(@RequestBody XiLeWangUser xiLeWangUser);

}
