package com.wuwei.consumer.wechat.config;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import jd.union.open.goods.query.response.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqProcessConfig {

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    @Autowired
    private XiLeWangHistoryService xiLeWangHistoryService;

    @RabbitListener(queues = "xilewang_history_insert")
    public void xiLeWangHistoryInsert(XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory || null == xiLeWangHistory.getSkuId()){
            return;
        }
        GoodsResp goodsResp = xiLeWangGoodsService.goodsDetail(xiLeWangHistory.getSkuId());
        if(null != goodsResp){
            xiLeWangHistory.setSkuName(goodsResp.getSkuName());
            ShopInfo[] shopInfos = goodsResp.getShopInfo();
            if(null != shopInfos && shopInfos.length > 0){
                ShopInfo shopInfo = shopInfos[0];
                if(null != shopInfo){
                    xiLeWangHistory.setShopId(shopInfo.getShopId());
                    xiLeWangHistory.setShopName(shopInfo.getShopName());
                }
            }
            CategoryInfo[] categoryInfos = goodsResp.getCategoryInfo();
            if(null != categoryInfos && categoryInfos.length > 0){
                CategoryInfo categoryInfo = categoryInfos[0];
                if(null != categoryInfo){
                    xiLeWangHistory.setCategoryOneId(categoryInfo.getCid1());
                    xiLeWangHistory.setCategoryOneName(categoryInfo.getCid1Name());
                    xiLeWangHistory.setCategoryTwoId(categoryInfo.getCid2());
                    xiLeWangHistory.setCategoryTwoName(categoryInfo.getCid2Name());
                    xiLeWangHistory.setCategoryThreeId(categoryInfo.getCid3());
                    xiLeWangHistory.setCategoryThreeName(categoryInfo.getCid3Name());
                }
            }
            xiLeWangHistoryService.insertSelective(xiLeWangHistory);
        }

    }

}
