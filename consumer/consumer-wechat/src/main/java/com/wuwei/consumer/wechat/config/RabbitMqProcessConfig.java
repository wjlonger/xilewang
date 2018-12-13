package com.wuwei.consumer.wechat.config;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceService;
import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import jd.union.open.goods.query.response.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Configuration
public class RabbitMqProcessConfig {

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    @Autowired
    private XiLeWangHistoryService xiLeWangHistoryService;

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @Autowired
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @Value("${goods.ratio}")
    private BigDecimal ratio;

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

    @RabbitListener(queues = "xilewang_order_insert")
    public void xiLeWangOrderInsert(XiLeWangOrder xiLeWangOrder){
        if(null == xiLeWangOrder || StringUtils.isEmpty(xiLeWangOrder.getSkuId()) || StringUtils.isEmpty(xiLeWangOrder.getOpenid())){
            return;
        }
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByOpenIdAndSkuId(xiLeWangOrder.getOpenid(),xiLeWangOrder.getSkuId());
        if(null == xiLeWangAssistance){
            xiLeWangOrder.setInitialRatio(ratio);
            xiLeWangOrder.setAssistanceId(0L);
        }else{
            xiLeWangOrder.setInitialRatio(xiLeWangAssistance.getInitialRatio());
            xiLeWangOrder.setAssistanceId(xiLeWangAssistance.getId());
        }
        xiLeWangOrderService.insertSelective(xiLeWangOrder);
    }

}
