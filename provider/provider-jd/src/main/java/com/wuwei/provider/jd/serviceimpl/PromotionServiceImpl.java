package com.wuwei.provider.jd.serviceimpl;

import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.model.PromotionSearch;
import com.wuwei.base.jd.service.PromotionService;
import jd.union.open.promotion.bysubunionid.get.request.UnionOpenPromotionBysubunionidGetRequest;
import jd.union.open.promotion.bysubunionid.get.response.UnionOpenPromotionBysubunionidGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private JdClient client;

    @Override
    public String GetBySubUnionId(PromotionSearch promotionSearch) {

        UnionOpenPromotionBysubunionidGetRequest request = new UnionOpenPromotionBysubunionidGetRequest();
        jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq promotionCodeReq = new jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq();
        if(null != promotionSearch){
            promotionCodeReq.setMaterialId(promotionSearch.getMaterialId());
            promotionCodeReq.setSubUnionId(promotionSearch.getSubUnionId());
            promotionCodeReq.setPositionId(promotionSearch.getPositionId());
            promotionCodeReq.setCouponUrl(promotionSearch.getCouponUrl());
            promotionCodeReq.setPid(promotionSearch.getPid());
        }
        request.setPromotionCodeReq(promotionCodeReq);
        UnionOpenPromotionBysubunionidGetResponse response = null;
        try {
            response = client.execute(request);
            if(response != null){
                return response.getData().getShortURL();
            }
        } catch (JdException e) {
        }
        return null;
    }

}
