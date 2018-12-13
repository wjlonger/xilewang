package com.wuwei.provider.jd.serviceimpl;

import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.service.PromotionService;
import jd.union.open.promotion.bysubunionid.get.request.UnionOpenPromotionBysubunionidGetRequest;
import jd.union.open.promotion.bysubunionid.get.response.UnionOpenPromotionBysubunionidGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jd.union.open.promotion.bysubunionid.get.request.PromotionCodeReq;
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private JdClient jdClient;

    @Override
    public String getBySubUnionId(PromotionCodeReq promotionCodeReq) {
        UnionOpenPromotionBysubunionidGetRequest request = new UnionOpenPromotionBysubunionidGetRequest();
        if(null == promotionCodeReq){
            promotionCodeReq = new PromotionCodeReq();
        }
        request.setPromotionCodeReq(promotionCodeReq);
        UnionOpenPromotionBysubunionidGetResponse response = null;
        try {
            response = jdClient.execute(request);
            if(null != response && null != response.getData()){
                return response.getData().getShortURL();
            }
        } catch (JdException e) {
        }
        return null;
    }

}
