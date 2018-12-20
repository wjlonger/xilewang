package com.wuwei.consumer.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.hystric.XiLeWangUserServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangUserServiceHystric.class, path = "/xilewang/user")
public interface XiLeWangUserService{

    @RequestLine("GET /code2Session/{code}?inviteCode={inviteCode}")
    String code2Session(@Param("code") final String code, @Param("inviteCode") String inviteCode);

    @RequestLine("GET /{openid}")
    XiLeWangUser selectByPrimaryKey (@Param("openid") final String openid);

    @RequestLine("POST /insertSelective")
    int insertSelective(@RequestBody XiLeWangUser xiLeWangUser);

    @RequestLine("POST /save")
    int save(@RequestBody XiLeWangUser xiLeWangUser);

    @RequestLine("GET /listByMasterOpenid/{openid}?pageNo={pageNo}&pageSize={pageSize}")
    PageInfo<XiLeWangUser> listByMasterOpenid(@Param("openid") String openid,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    @RequestLine("GET /inviteCount/{openid}")
    Integer inviteCount(@Param("openid") String openid);

}
