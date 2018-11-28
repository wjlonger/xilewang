package com.wuwei.provider.jd.serviceimpl;

import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.model.GoodsSearch;
import com.wuwei.base.jd.service.HomeService;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.request.UnionOpenGoodsQueryRequest;
import jd.union.open.goods.query.response.CommissionInfo;
import jd.union.open.goods.query.response.GoodsResp;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private JdClient jdClient;

    @Value("${goods.radio}")
    private double radio;

    @Override
    public String slideShow() {
        return "{\"reason\":\"\",\"code\":\"0\",\"list\":[{\"advertUrl\":\"/pages/home_detail?code=020\",\"createTime\":{\"date\":28,\"day\":4,\"hours\":14,\"minutes\":11,\"month\":11,\"nanos\":0,\"seconds\":38,\"time\":1514441498000,\"timezoneOffset\":-480,\"year\":117},\"id\":\"2c9257a1609b12a301609bbf5e790169\",\"isdeleted\":0,\"limitFowards\":0,\"limitPrice\":0,\"locationFlag\":1,\"picUrl\":\"http://sujiefs.com/upload/images/20180319/201803191442069389248.jpg\",\"presentAmout\":0,\"promoDesc\":\"\",\"promoPicUrl\":\"\",\"promoTips\":\"\",\"sort\":5,\"status\":1,\"title\":\"嘉年华\",\"type\":1,\"updateTime\":{\"date\":9,\"day\":6,\"hours\":12,\"minutes\":24,\"month\":5,\"nanos\":0,\"seconds\":50,\"time\":1528518290000,\"timezoneOffset\":-480,\"year\":118}},{\"advertUrl\":\"/pages/home_detail?code=019\",\"createTime\":{\"date\":29,\"day\":5,\"hours\":16,\"minutes\":41,\"month\":11,\"nanos\":0,\"seconds\":58,\"time\":1514536918000,\"timezoneOffset\":-480,\"year\":117},\"id\":\"2c9257a1609b12a30160a16f5d9a039b\",\"isdeleted\":0,\"limitFowards\":0,\"limitPrice\":0,\"locationFlag\":1,\"picUrl\":\"http://sujiefs.com/upload/images/20180322/201803221353348299896.jpg\",\"presentAmout\":0,\"promoDesc\":\"\",\"promoPicUrl\":\"\",\"promoTips\":\"\",\"sort\":11,\"status\":1,\"title\":\"时尚英伦风\",\"type\":1,\"updateTime\":{\"date\":22,\"day\":4,\"hours\":13,\"minutes\":53,\"month\":2,\"nanos\":0,\"seconds\":36,\"time\":1521698016000,\"timezoneOffset\":-480,\"year\":118}},{\"advertUrl\":\"/pages/home_detail?code=020\",\"createTime\":{\"date\":20,\"day\":5,\"hours\":17,\"minutes\":29,\"month\":9,\"nanos\":0,\"seconds\":14,\"time\":1508491754000,\"timezoneOffset\":-480,\"year\":117},\"id\":\"2c9257a15f37e432015f391d7d8d00d5\",\"isdeleted\":0,\"limitFowards\":0,\"limitPrice\":0,\"locationFlag\":1,\"picUrl\":\"http://sujiefs.com/upload/images/20180321/201803211341067195861.jpg\",\"presentAmout\":0,\"promoDesc\":\"\",\"promoPicUrl\":\"\",\"promoTips\":\"\",\"sort\":12,\"status\":1,\"title\":\"趁年轻穿自己想穿的\",\"type\":1,\"updateTime\":{\"date\":21,\"day\":3,\"hours\":13,\"minutes\":41,\"month\":2,\"nanos\":0,\"seconds\":8,\"time\":1521610868000,\"timezoneOffset\":-480,\"year\":118}},{\"advertUrl\":\"/pages/home_detail?code=020\",\"createTime\":{\"date\":5,\"day\":4,\"hours\":19,\"minutes\":40,\"month\":9,\"nanos\":0,\"seconds\":45,\"time\":1507203645000,\"timezoneOffset\":-480,\"year\":117},\"id\":\"2c9257a15eaf3ade015eec56806f08a9\",\"isdeleted\":0,\"limitFowards\":0,\"limitPrice\":0,\"locationFlag\":1,\"picUrl\":\"http://sujiefs.com/upload/images/20180322/201803221355480509362.jpg\",\"presentAmout\":0,\"promoDesc\":\"\",\"promoPicUrl\":\"\",\"promoTips\":\"\",\"sort\":13,\"status\":1,\"title\":\"热销潮流必买单品\",\"type\":1,\"updateTime\":{\"date\":22,\"day\":4,\"hours\":13,\"minutes\":55,\"month\":2,\"nanos\":0,\"seconds\":48,\"time\":1521698148000,\"timezoneOffset\":-480,\"year\":118}},{\"advertUrl\":\"/pages/home_detail?code=019\",\"createTime\":{\"date\":29,\"day\":5,\"hours\":17,\"minutes\":4,\"month\":11,\"nanos\":0,\"seconds\":15,\"time\":1514538255000,\"timezoneOffset\":-480,\"year\":117},\"id\":\"2c9257a1609b12a30160a183c3ff03a5\",\"isdeleted\":0,\"limitFowards\":0,\"limitPrice\":0,\"locationFlag\":1,\"picUrl\":\"http://sujiefs.com/upload/images/20180322/201803221356007729116.jpg\",\"presentAmout\":0,\"promoDesc\":\"\",\"promoPicUrl\":\"\",\"promoTips\":\"\",\"sort\":15,\"status\":1,\"title\":\"秋冬\",\"type\":1,\"updateTime\":{\"date\":22,\"day\":4,\"hours\":13,\"minutes\":56,\"month\":2,\"nanos\":0,\"seconds\":1,\"time\":1521698161000,\"timezoneOffset\":-480,\"year\":118}}]}";
    }

    @Override
    public UnionOpenGoodsQueryResponse explosiveGoods(GoodsSearch goodsSearch) {
        UnionOpenGoodsQueryRequest request = new UnionOpenGoodsQueryRequest();
        GoodsReq goodsReq = new GoodsReq();
        if(null != goodsSearch){
            goodsReq.setCid1(goodsSearch.getCid1());
            goodsReq.setCid2(goodsSearch.getCid2());
            goodsReq.setCid3(goodsSearch.getCid3());
            goodsReq.setPageIndex(goodsSearch.getPageIndex());
            goodsReq.setPageSize(goodsSearch.getPageSize());
            if(null != goodsSearch.getSkuIds() && goodsSearch.getSkuIds().size() > 0){
                goodsReq.setSkuIds((Long[])goodsSearch.getSkuIds().toArray());
            }
            goodsReq.setKeyword(goodsSearch.getKeyword());
            goodsReq.setPricefrom(goodsSearch.getPricefrom());
            goodsReq.setPriceto(goodsSearch.getPriceto());
            goodsReq.setCommissionShareStart(goodsSearch.getCommissionShareStart());
            goodsReq.setCommissionShareEnd(goodsSearch.getCommissionShareEnd());
            goodsReq.setOwner(goodsSearch.getOwner());
            goodsReq.setSortName(goodsSearch.getSortName());
            goodsReq.setSort(goodsSearch.getSort());
            goodsReq.setIsCoupon(goodsSearch.getIsCoupon());
            goodsReq.setIsPG(goodsSearch.getIsPG());
            goodsReq.setPingouPriceStart(goodsSearch.getPingouPriceStart());
            goodsReq.setPingouPriceEnd(goodsSearch.getPingouPriceEnd());
            goodsReq.setIsHot(goodsSearch.getIsHot());
            goodsReq.setBrandCode(goodsSearch.getBrandCode());
            goodsReq.setShopId(goodsSearch.getShopId());
        }

        UnionOpenGoodsQueryResponse response = null;
        request.setGoodsReqDTO(goodsReq);
        try {
            response = jdClient.execute(request);
            if(null != response){
                GoodsResp[] goodsResps = response.getData();
                if(null != goodsResps && goodsResps.length > 0){
                    for(GoodsResp goodsResp : goodsResps){
                        CommissionInfo[] commissionInfos = goodsResp.getCommissionInfo();
                        if(null != commissionInfos && commissionInfos.length > 0){
                            for(CommissionInfo commissionInfo : commissionInfos){
                                commissionInfo.setCommission(new BigDecimal(commissionInfo.getCommission()).multiply(new BigDecimal(radio)).divide(new BigDecimal(100)).doubleValue());
                            }
                        }
                    }
                }
            }
        } catch (JdException e) {
        }
        return response;
    }

}
