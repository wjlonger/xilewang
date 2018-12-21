package com.wuwei.consumer.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;
import com.wuwei.consumer.wechat.hystric.XiLeWangFavoriteServiceHystric;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-wechat",fallback= XiLeWangFavoriteServiceHystric.class, path = "/xilewang/favorite")
public interface XiLeWangFavoriteService {

    @RequestLine("POST /")
    int insertSelective(@RequestBody XiLeWangFavorite xiLeWangFavorite);

    @RequestLine("GET /{openid}/{skuId}")
    XiLeWangFavorite selectByPrimaryKey(@Param("openid") String openid, @Param("skuId") Long skuId);

    @RequestLine("PUT /")
    int updateByPrimaryKeySelective(@RequestBody XiLeWangFavorite xiLeWangFavorite);

    @RequestLine("GET /{openid}?pageNo={pageNo}&pageSize={pageSize}")
    PageInfo<XiLeWangFavoriteVo> selectByOpenid(@Param("openid") String openid, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


}
