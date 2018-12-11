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
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @Autowired
    private AmqpTemplate amqpTemplate;

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
                            String openid = "";
                            if(null != skuInfos && skuInfos.length > 0){
                                for(SkuInfo skuInfo : skuInfos){
                                    if(null != skuInfo && skuInfo.getEstimateFee() > 0.0){
                                        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = new XiLeWangJdOrderSkuInfo(skuInfo);
                                        //region 获取商品图
                                        GoodsResp goodsResp = goodsService.goodsDetail(skuInfo.getSkuId());
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
                                        //region 计算佣金
                                        XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(Long.parseLong(skuInfo.getSubUnionId()));
                                        if(openid.length() == 0){
                                            openid = xiLeWangOrder.getOpenid();
                                        }
                                        if(null != xiLeWangOrder){
                                            //region 找到订单记录
                                            //region 计算佣金比例
                                            BigDecimal ratio = BigDecimal.valueOf(0);
                                            XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(xiLeWangOrder.getAssistanceId());
                                            if(null != xiLeWangAssistance){
                                                ratio.add(xiLeWangAssistance.getInitialRatio());
                                            }
                                            //endregion
                                            if(skuInfo.getSkuId().equals(xiLeWangOrder.getSkuId())){
                                                //region 是助力的商品
                                                List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                                                if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                                                    for(XiLeWangAssistanceUser xiLeWangAssistanceUser : xiLeWangAssistanceUsers){
                                                        if(null != xiLeWangAssistanceUser){
                                                            ratio.add(xiLeWangAssistanceUser.getAssistanceRatio());
                                                            //region 计算奖励
                                                            BigDecimal reward = BigDecimal.valueOf(skuInfo.getEstimateFee()).multiply(xiLeWangAssistanceUser.getRewardRatio()).divide(BigDecimal.valueOf(100));
                                                            if(reward.compareTo(BigDecimal.valueOf(0.01)) == -1){
                                                                reward = BigDecimal.valueOf(0.01);
                                                            }
                                                            XiLeWangIncomeReport xiLeWangIncomeReport = xiLeWangIncomeReportService.selectByOpenidAndJdOrderIdAndSkuId(xiLeWangOrder.getOpenid(),orderResp.getOrderId(),skuInfo.getSkuId());
                                                            if(null == xiLeWangIncomeReport){
                                                                xiLeWangIncomeReport = new XiLeWangIncomeReport();
                                                                xiLeWangIncomeReport.setId(IdGenerator.nextId());
                                                                xiLeWangIncomeReport.setType(1);
                                                                xiLeWangIncomeReport.setState(skuInfo.getValidCode());
                                                                xiLeWangIncomeReport.setOpenid(xiLeWangAssistanceUser.getOpenid());
                                                                xiLeWangIncomeReport.setJdOrderId(orderResp.getOrderId());
                                                                xiLeWangIncomeReport.setJdOrderSkuId(skuInfo.getSkuId());
                                                                xiLeWangIncomeReport.setMoney(reward);
                                                                xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                                                            }else{
                                                                xiLeWangIncomeReport.setState(skuInfo.getValidCode());
                                                                xiLeWangIncomeReport.setMoney(reward);
                                                                xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                                                            }
                                                            if(skuInfo.getValidCode().equals(18)){
                                                                xiLeWangUserService.updateMoneyByPrimaryKey(1,reward,xiLeWangAssistanceUser.getOpenid());
                                                            }
                                                            //endregion
                                                        }
                                                    }
                                                }
                                                BigDecimal rebatePrice = BigDecimal.valueOf(skuInfo.getEstimateFee()).multiply(ratio).divide(BigDecimal.valueOf(100));
                                                if(rebatePrice.compareTo(BigDecimal.valueOf(0.01)) == -1){
                                                    rebatePrice = BigDecimal.valueOf(0.01);
                                                }
                                                xiLeWangJdOrderSkuInfo.setRebatePrice(rebatePrice);
                                                //endregion
                                            }else{
                                                //region 不是助力的商品
                                                BigDecimal rebatePrice = BigDecimal.valueOf(skuInfo.getEstimateFee()).multiply(ratio).divide(BigDecimal.valueOf(100));
                                                if(rebatePrice.compareTo(BigDecimal.valueOf(0.01)) == -1){
                                                    rebatePrice = BigDecimal.valueOf(0.01);
                                                }
                                                xiLeWangJdOrderSkuInfo.setRebatePrice(rebatePrice);
                                                //endregion
                                            }
                                            //endregion
                                            XiLeWangIncomeReport xiLeWangIncomeReport = xiLeWangIncomeReportService.selectByOpenidAndJdOrderIdAndSkuId(xiLeWangOrder.getOpenid(),orderResp.getOrderId(),skuInfo.getSkuId());
                                            if(null == xiLeWangIncomeReport){
                                                xiLeWangIncomeReport = new XiLeWangIncomeReport();
                                                xiLeWangIncomeReport.setId(IdGenerator.nextId());
                                                xiLeWangIncomeReport.setType(0);
                                                xiLeWangIncomeReport.setState(skuInfo.getValidCode());
                                                xiLeWangIncomeReport.setOpenid(xiLeWangOrder.getOpenid());
                                                xiLeWangIncomeReport.setJdOrderId(orderResp.getOrderId());
                                                xiLeWangIncomeReport.setJdOrderSkuId(skuInfo.getSkuId());
                                                xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice());
                                                xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                                            }else{
                                                xiLeWangIncomeReport.setState(skuInfo.getValidCode());
                                                xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice());
                                                xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                                            }
                                            if(skuInfo.getValidCode().equals(18)){
                                                xiLeWangUserService.updateMoneyByPrimaryKey(0,xiLeWangJdOrderSkuInfo.getRebatePrice(),xiLeWangOrder.getOpenid());
                                            }
                                            XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(xiLeWangOrder.getOpenid());
                                            boolean hasMaster = null != xiLeWangUser && !StringUtils.isNullOrEmpty(xiLeWangUser.getMasterOpenid());
                                            if(hasMaster){
                                                BigDecimal reward = xiLeWangJdOrderSkuInfo.getRebatePrice().multiply(masterRatio).divide(BigDecimal.valueOf(100));
                                                if(reward.compareTo(BigDecimal.valueOf(0.01)) == -1){
                                                    reward = BigDecimal.valueOf(0.01);
                                                }
                                                xiLeWangIncomeReport = xiLeWangIncomeReportService.selectByOpenidAndJdOrderIdAndSkuId(xiLeWangUser.getMasterOpenid(),orderResp.getOrderId(),skuInfo.getSkuId());
                                                if(null == xiLeWangIncomeReport){
                                                    xiLeWangIncomeReport = new XiLeWangIncomeReport();
                                                    xiLeWangIncomeReport.setId(IdGenerator.nextId());
                                                    xiLeWangIncomeReport.setType(2);
                                                    xiLeWangIncomeReport.setState(skuInfo.getValidCode());
                                                    xiLeWangIncomeReport.setOpenid(xiLeWangOrder.getOpenid());
                                                    xiLeWangIncomeReport.setJdOrderId(orderResp.getOrderId());
                                                    xiLeWangIncomeReport.setJdOrderSkuId(skuInfo.getSkuId());
                                                    xiLeWangIncomeReport.setMoney(reward);
                                                    xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                                                }else{
                                                    xiLeWangIncomeReport.setState(skuInfo.getValidCode());
                                                    xiLeWangIncomeReport.setMoney(reward);
                                                    xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                                                }
                                                if(skuInfo.getValidCode().equals(18)){
                                                    xiLeWangUserService.updateMoneyByPrimaryKey(0,xiLeWangJdOrderSkuInfo.getRebatePrice(),xiLeWangOrder.getOpenid());
                                                }
                                            }
                                        }else{
                                            //region 未找到订单记录
                                            xiLeWangJdOrderSkuInfo.setRebatePrice(BigDecimal.valueOf(0));
                                            //endregion
                                        }
                                        //endregion
                                        //region 存储
                                        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfoTemp = xiLeWangJdOrderSkuInfoService.selectBySkuIdAndOrderId(skuInfo.getSkuId(),orderResp.getOrderId());
                                        if(null == xiLeWangJdOrderSkuInfoTemp){
                                            xiLeWangJdOrderSkuInfo.setId(IdGenerator.nextId());
                                            xiLeWangJdOrderSkuInfoService.insertSelective(xiLeWangJdOrderSkuInfo);
                                        }else{
                                            xiLeWangJdOrderSkuInfo.setId(xiLeWangJdOrderSkuInfoTemp.getId());
                                            xiLeWangJdOrderSkuInfoService.insertSelective(xiLeWangJdOrderSkuInfo);
                                        }
                                        //endregion
                                    }
                                }
                                //endregion
                            }
                            xiLeWangJdOrder.setOpenid(openid);
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
