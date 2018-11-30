package com.wuwei.consumer.wechat.service;

import com.wuwei.consumer.wechat.hystric.XiLeWangPromotionServiceHystric;
import feign.RequestLine;
import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-jd",fallback= XiLeWangPromotionServiceHystric.class, path = "/promotion")
public interface XiLeWangPromotionService extends com.wuwei.base.jd.service.PromotionService {

    @Override
    @RequestLine("POST /getBySubUnionId")
    String getBySubUnionId(@RequestBody PromotionCodeReq promotionCodeReq);

}
