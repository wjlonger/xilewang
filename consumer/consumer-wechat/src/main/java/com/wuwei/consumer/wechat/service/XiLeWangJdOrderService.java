package com.wuwei.consumer.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.vo.XiLeWangJdOrderVo;
import com.wuwei.consumer.wechat.hystric.XiLeWangJdOrderServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider-wechat",fallback= XiLeWangJdOrderServiceHystric.class, path = "/xilewang/jdorder")
public interface XiLeWangJdOrderService {

    @RequestLine("GET /listByOpenidAndViladCode/{openid}?pageNo={pageNo}&pageSize={pageSize}&validCode={validCode}")
    PageInfo<XiLeWangJdOrderVo> listByOpenidAndViladCode(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("openid") String openid, @Param("validCode") Integer validCode);

}
