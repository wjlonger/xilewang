package com.wuwei.consumer.wechat.hystric;

import com.wuwei.consumer.wechat.service.XiLeWangPromotionService;
import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangPromotionServiceHystric implements XiLeWangPromotionService {

    @Override
    public String getBySubUnionId(PromotionCodeReq promotionCodeReq) {
        return null;
    }
}
