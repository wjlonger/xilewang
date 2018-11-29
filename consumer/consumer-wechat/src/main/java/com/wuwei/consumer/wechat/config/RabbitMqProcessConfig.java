package com.wuwei.consumer.wechat.config;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.base.wechat.model.XiLeWangOrder;
import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import com.wuwei.consumer.wechat.service.XiLeWangOrderService;
import jd.union.open.goods.query.response.GoodsResp;
import jd.union.open.goods.query.response.ImageInfo;
import jd.union.open.goods.query.response.UrlInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class RabbitMqProcessConfig {

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    @Autowired
    private XiLeWangHistoryService xiLeWangHistoryService;

    @Value("${goods.radio}")
    private double radio;

    @RabbitListener(queues="xilewang_order_insert")
    public void xiLeWangOrderInsert(XiLeWangOrder xiLeWangOrder) {
        GoodsResp goodsResp = xiLeWangGoodsService.goodsDetail(xiLeWangOrder.getSkuId());
        xiLeWangOrder.setSkuName(goodsResp.getSkuName());
        xiLeWangOrder.setCommission(goodsResp.getCommissionInfo()[0].getCommission());
        xiLeWangOrder.setCommissionShare(goodsResp.getCommissionInfo()[0].getCommissionShare());
        StringBuilder sb = new StringBuilder();
        for(ImageInfo imageInfo : goodsResp.getImageInfo()){
            for(UrlInfo urlInfo : imageInfo.getImageList()){
                sb.append(urlInfo.getUrl());
            }
        }
        xiLeWangOrder.setImages(sb.toString());
        xiLeWangOrder.setPrice(goodsResp.getPriceInfo()[0].getPrice());
        xiLeWangOrder.setRatio(radio);
        xiLeWangOrder.setExpectMoney(new BigDecimal(xiLeWangOrder.getCommission()).multiply(new BigDecimal(radio)).divide(new BigDecimal(100)));
        xiLeWangOrderService.insertSelective(xiLeWangOrder);
    }

    @RabbitListener(queues = "xilewang_history_insert")
    public void xiLeWangHistoryInsert(XiLeWangHistory xiLeWangHistory){
        xiLeWangHistoryService.insertSelective(xiLeWangHistory);
    }

}
