package com.wuwei.consumer.quartz.config;

import com.wuwei.base.util.CollectionUtils;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.util.StringUtils;
import com.wuwei.base.wechat.model.*;
import com.wuwei.consumer.quartz.service.*;
import jd.union.open.goods.query.response.GoodsResp;
import jd.union.open.goods.query.response.ImageInfo;
import jd.union.open.goods.query.response.UrlInfo;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.OrderResp;
import jd.union.open.order.query.response.SkuInfo;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class RabbitMqProcessConfig {

    @Autowired
    private JdOrderService jdOrderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private XiLeWangJdOrderService xiLeWangJdOrderService;

    @Autowired
    private XiLeWangJdOrderSkuInfoService xiLeWangJdOrderSkuInfoService;

    @Autowired
    private XiLeWangOrderService xiLeWangOrderService;

    @Autowired
    private XiLeWangAssistanceUserService xiLeWangAssistanceUserService;

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @Autowired
    private XiLeWangIncomeReportService xiLeWangIncomeReportService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${goods.ratio}")
    private BigDecimal initialRatio;

    @Value("${master.ratio}")
    private BigDecimal masterRatio;

    @RabbitListener(queues = "quartz_jdorder_save")
    public void quartzJdOrderSave(OrderReq orderReq){
        if(null != orderReq){
            UnionOpenOrderQueryResponse unionOpenOrderQueryResponse = jdOrderService.query(orderReq);
            if(null != unionOpenOrderQueryResponse){
                OrderResp[] orderResps = unionOpenOrderQueryResponse.getData();
                if(null != orderResps && orderResps.length > 0){
                    for(OrderResp orderResp : orderResps){
                        //region 处理订单
                        if(null != orderResp){
                            XiLeWangJdOrder xiLeWangJdOrder = new XiLeWangJdOrder(orderResp);
                            SkuInfo[] skuInfos = orderResp.getSkuList();
                            if(null != skuInfos && skuInfos.length > 0){
                                BigDecimal ratio = new BigDecimal(0);
                                Long skuId = null;
                                boolean hasMaster = false;
                                List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = null;
                                Long xiLeWangOrderId = Long.parseLong(skuInfos[0].getSubUnionId());
                                XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(xiLeWangOrderId);
                                //region 处理商品
                                if(null != xiLeWangOrder){
                                    ratio.add(xiLeWangOrder.getInitialRatio());
                                    skuId = xiLeWangOrder.getSkuId();
                                    //region 获取师傅
                                    XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(xiLeWangOrder.getOpenid());
                                    hasMaster = null != xiLeWangUser && !StringUtils.isNullOrEmpty(xiLeWangUser.getMasterOpenid());
                                    //endregion
                                    //region 获得助力比例
                                    if(null != xiLeWangOrder.getAssistanceId() && 0 != xiLeWangOrder.getAssistanceId()){
                                        xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                                        if(null != xiLeWangAssistanceUsers && !CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                                            for(XiLeWangAssistanceUser xiLeWangAssistanceUser : xiLeWangAssistanceUsers){
                                                ratio.add(xiLeWangAssistanceUser.getAssistanceRatio());
                                            }
                                        }
                                    }
                                    //endregion
                                    for(SkuInfo skuInfo : skuInfos){
                                        if(null != skuInfo){
                                            XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = new XiLeWangJdOrderSkuInfo(skuInfo);
                                            xiLeWangJdOrderSkuInfo.setJdOrderId(xiLeWangJdOrder.getOrderId());
                                            //region 获取商品图
                                            GoodsResp goodsResp = goodsService.goodsDetail(xiLeWangJdOrderSkuInfo.getSkuId());
                                            if(null != goodsResp){
                                                ImageInfo[] imageInfos = goodsResp.getImageInfo();
                                                if(null != imageInfos && imageInfos.length > 0){
                                                    UrlInfo[] urlInfos = imageInfos[0].getImageList();
                                                    if(null != urlInfos && urlInfos.length > 0){
                                                        xiLeWangJdOrderSkuInfo.setImg(urlInfos[0].getUrl());
                                                    }
                                                }
                                            }
                                            //endregion
                                            if(skuInfo.getSkuId().equals(skuId)){
                                                xiLeWangJdOrderSkuInfo.setRebatePrice(new BigDecimal(skuInfo.getEstimateFee()).multiply(ratio).divide(new BigDecimal(100)));
                                            }else{
                                                xiLeWangJdOrderSkuInfo.setRebatePrice(new BigDecimal(skuInfo.getEstimateFee()).multiply(xiLeWangOrder.getInitialRatio()).divide(new BigDecimal(100)));
                                            }
                                            XiLeWangJdOrderSkuInfo temp = xiLeWangJdOrderSkuInfoService.selectBySkuIdAndOrderId(xiLeWangJdOrderSkuInfo.getSkuId(),xiLeWangJdOrderSkuInfo.getJdOrderId());
                                            if(null == temp){
                                                xiLeWangJdOrderSkuInfo.setId(IdGenerator.nextId());
                                                xiLeWangJdOrderSkuInfoService.insertSelective(xiLeWangJdOrderSkuInfo);
                                            }else{
                                                xiLeWangJdOrderSkuInfo.setId(temp.getId());
                                                xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfo);
                                            }
                                        }
                                    }
                                }
                                //endregion
                            }
                            if(null == xiLeWangJdOrderService.selectByPrimaryKey(xiLeWangJdOrder.getOrderId())){
                                xiLeWangJdOrderService.insertSelective(xiLeWangJdOrder);
                            }else{
                                xiLeWangJdOrderService.updateByPrimaryKeySelective(xiLeWangJdOrder);
                            }
                        }
                        //endregion
                    }
                }
                if(unionOpenOrderQueryResponse.getHasMore()){
                    orderReq.setPageNo(orderReq.getPageNo() + 1);
                    amqpTemplate.convertAndSend("quartz_jdorder_save",orderReq);
                }
            }
        }
    }

}
