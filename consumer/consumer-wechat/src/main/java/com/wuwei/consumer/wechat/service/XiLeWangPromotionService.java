package com.wuwei.consumer.wechat.service;

import com.wuwei.base.jd.model.PromotionSearch;
import com.wuwei.consumer.wechat.hystric.XiLeWangPromotionServiceHystric;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "provider-jd",fallback= XiLeWangPromotionServiceHystric.class, path = "/promotion")
public interface XiLeWangPromotionService extends com.wuwei.base.jd.service.PromotionService {

    @Override
    @RequestLine("POST /GetBySubUnionId")
    String GetBySubUnionId(@RequestBody PromotionSearch promotionSearch);

}
