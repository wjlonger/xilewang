package com.wuwei.consumer.quartz.config;

import com.wuwei.base.quartz.model.XiLeWangQuartz;
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

    @RabbitListener(queues = "quartz_jdorder_save")
    public void quartzJdOrderSave(OrderReq orderReq){
        if(null != orderReq){
            UnionOpenOrderQueryResponse unionOpenOrderQueryResponse = jdOrderService.query(orderReq);
            if(null != unionOpenOrderQueryResponse){
                OrderResp[] orderResps = unionOpenOrderQueryResponse.getData();
                if(null != orderResps && orderResps.length > 0){
                    for(OrderResp orderResp : orderResps) {
                        // validCode详见 https://media.jd.com/jhtml/page/apidetail/apidetail.html
                        if (null != orderResp && orderResp.getValidCode() > 2) {
                            XiLeWangJdOrder xiLeWangJdOrder = new XiLeWangJdOrder(orderResp);
                            SkuInfo[] skuInfos = orderResp.getSkuList();
                            String openid = "";
                            int length = skuInfos.length;
                            if (null != skuInfos && length > 0) {
                                for(int i=0; i<length; i++){
                                    SkuInfo skuInfo = skuInfos[i];
                                    if(null != skuInfo){
                                        // 获取openid
                                        if(openid.length() == 0){
                                            Long orderid = Long.parseLong(skuInfo.getSubUnionId());
                                            XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderid);
                                            if(null != xiLeWangOrder){
                                                openid = xiLeWangOrder.getOpenid();
                                            }
                                        }
                                        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = new XiLeWangJdOrderSkuInfo(skuInfo);
                                        // 京东订单ID
                                        xiLeWangJdOrderSkuInfo.setJdOrderId(orderResp.getOrderId());
                                        boolean needProcess = xiLeWangJdOrderSkuInfo.getValidCode() > 14;
                                        if(needProcess){
                                            // 需要消息队列处理预估佣金
                                            xiLeWangJdOrderSkuInfo.setState(0);
                                        }else{
                                            // 不需要消息队列处理预估佣金
                                            xiLeWangJdOrderSkuInfo.setState(1);
                                        }
                                        // 将钱归零，交由消息队重新进行计算
                                        xiLeWangJdOrderSkuInfo.setRebatePrice(BigDecimal.valueOf(0));
                                        // sku index
                                        xiLeWangJdOrderSkuInfo.setSkuIndex(i);
                                        int result = 0;
                                        XiLeWangJdOrderSkuInfo temp = xiLeWangJdOrderSkuInfoService.selectByOrderIdAndSkuIndex(orderResp.getOrderId(),i);
                                        if(null == temp){
                                            // 执行插入逻辑
                                            xiLeWangJdOrderSkuInfo.setId(IdGenerator.nextId());
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
                                            result = xiLeWangJdOrderSkuInfoService.insertSelective(xiLeWangJdOrderSkuInfo);
                                        }else{
                                            // 执行更新逻辑
                                            xiLeWangJdOrderSkuInfo.setId(temp.getId());
                                            result = xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfo);
                                        }
                                        if(result > 0 && needProcess){
                                            // 发消息处理金额
                                            amqpTemplate.convertAndSend("quartz_sku_rebate_price",xiLeWangJdOrderSkuInfo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(unionOpenOrderQueryResponse.getHasMore()){
                    orderReq.setPageNo(orderReq.getPageNo() + 1);
                    amqpTemplate.convertAndSend("quartz_jdorder_save",orderReq);
                }
            }
        }
    }

    @RabbitListener(queues = "quartz_sku_rebate_price")
    public void quartzSkuRebatePrice(XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo){
        if(null != xiLeWangJdOrderSkuInfo){
            XiLeWangJdOrderSkuInfo temp = new XiLeWangJdOrderSkuInfo();
            temp.setId(xiLeWangJdOrderSkuInfo.getId());
            temp.setState(1);
            temp.setRebatePrice(BigDecimal.valueOf(0));
            BigDecimal rebate = xiLeWangJdOrderSkuInfo.getEstimateFee();
            // validCode详见 https://media.jd.com/jhtml/page/apidetail/apidetail.html
            // 18为已结算
            if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                rebate = xiLeWangJdOrderSkuInfo.getActualFee();
            }
            if(BigDecimal.valueOf(0).compareTo(rebate) == -1){
                // 预估佣金大于0
                BigDecimal ratio = BigDecimal.valueOf(0L);
                Long orderId = Long.parseLong(xiLeWangJdOrderSkuInfo.getSubUnionId());
                XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderId);
                if(null != xiLeWangOrder){
                    XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(xiLeWangOrder.getAssistanceId());
                    if(null != xiLeWangAssistance){
                        ratio = ratio.add(xiLeWangAssistance.getInitialRatio());
                        List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                        if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                            int length = Math.min(xiLeWangAssistanceUsers.size(),xiLeWangAssistance.getAssistancePeopleNum());
                            for(int i=0; i<length; i++){
                                XiLeWangAssistanceUser xiLeWangAssistanceUser = xiLeWangAssistanceUsers.get(i);
                                if(null != xiLeWangAssistanceUser){
                                    ratio = ratio.add(xiLeWangAssistanceUser.getAssistanceRatio());
                                }
                            }
                        }
                    }
                }
                temp.setRebatePrice(rebate.multiply(ratio).divide(BigDecimal.valueOf(100L)));
                // 少于1分钱 给1分
                if(BigDecimal.valueOf(0.01).compareTo(temp.getRebatePrice()) == 1){
                    temp.setRebatePrice(BigDecimal.valueOf(0.01));
                }
            }
            int result = xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(temp);
            if(result > 0){
                if(BigDecimal.valueOf(0L).compareTo(temp.getRebatePrice()) == -1){
                    // 有佣金
                    // 发送消息 写入收入明细表
                    amqpTemplate.convertAndSend("quartz_income_report_save",xiLeWangJdOrderSkuInfo.getId());
                }
            }
        }
    }

    @RabbitListener(queues = "quartz_income_report_save")
    public void quartzIncomeReportSave(Long skuInfoId){
        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = xiLeWangJdOrderSkuInfoService.selectByPrimaryKey(skuInfoId);
        if(null != xiLeWangJdOrderSkuInfo){
            Long orderId = Long.parseLong(xiLeWangJdOrderSkuInfo.getSubUnionId());
            XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderId);
            if(null != xiLeWangOrder){
                XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(xiLeWangOrder.getAssistanceId());
                if(null != xiLeWangAssistance){
                    List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                    if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                        int length = Math.min(xiLeWangAssistanceUsers.size(),xiLeWangAssistance.getAssistancePeopleNum());
                        for(int i=0; i<length; i++){
                            XiLeWangAssistanceUser xiLeWangAssistanceUser = xiLeWangAssistanceUsers.get(i);
                            if(null != xiLeWangAssistanceUser){

                            }
                        }
                    }
                }
            }
            XiLeWangJdOrderSkuInfo temp = new XiLeWangJdOrderSkuInfo();
            temp.setId(skuInfoId);
            temp.setState(2);
        }
    }
}
