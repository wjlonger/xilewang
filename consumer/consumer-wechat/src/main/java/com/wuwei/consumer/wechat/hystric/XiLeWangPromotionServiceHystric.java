package com.wuwei.consumer.wechat.hystric;

import com.wuwei.base.jd.model.PromotionSearch;
import com.wuwei.consumer.wechat.service.XiLeWangPromotionService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangPromotionServiceHystric implements XiLeWangPromotionService {

    @Override
    public String GetBySubUnionId(PromotionSearch promotionSearch) {
        return null;
    }
}
