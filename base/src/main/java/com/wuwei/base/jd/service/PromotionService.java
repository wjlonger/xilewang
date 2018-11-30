package com.wuwei.base.jd.service;

import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;

public interface PromotionService {

    String getBySubUnionId(PromotionCodeReq promotionCodeReq);

}
