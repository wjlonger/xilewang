package com.wuwei.provider.jd.serviceimpl;

import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.service.GoodsService;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.request.UnionOpenGoodsQueryRequest;
import jd.union.open.goods.query.response.GoodsResp;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsService")
public class GoodServiceImpl implements GoodsService {

    @Autowired
    private JdClient jdClient;

    @Override
    public GoodsResp goodsDetail(Long skuId) {
        UnionOpenGoodsQueryRequest request = new UnionOpenGoodsQueryRequest();
        GoodsReq goodsReq = new GoodsReq();
        goodsReq.setSkuIds(new Long[]{ skuId });
        UnionOpenGoodsQueryResponse response;
        request.setGoodsReqDTO(goodsReq);
        try {
            long time1 = System.currentTimeMillis();
            response = jdClient.execute(request);
            long time2 = System.currentTimeMillis();
            System.out.println(time2-time1);
            if(null != response && null != response.getData() && response.getData().length > 0){
                return response.getData()[0];
            }
        } catch (JdException e) {
        }
        return null;
    }

}
