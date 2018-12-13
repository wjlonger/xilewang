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
                            xiLeWangJdOrder.setOpenid(openid);
                            XiLeWangJdOrder temp = xiLeWangJdOrderService.selectByPrimaryKey(xiLeWangJdOrder.getOrderId());
                            if(null == temp){
                                xiLeWangJdOrderService.insertSelective(xiLeWangJdOrder);
                            }else{
                                xiLeWangJdOrderService.updateByPrimaryKeySelective(xiLeWangJdOrder);
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
                        BigDecimal rebate = xiLeWangJdOrderSkuInfo.getEstimateFee();
                        // validCode详见 https://media.jd.com/jhtml/page/apidetail/apidetail.html
                        // 18为已结算
                        if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                            rebate = xiLeWangJdOrderSkuInfo.getActualFee();
                        }
                        for(int i=0; i<length; i++){
                            XiLeWangAssistanceUser xiLeWangAssistanceUser = xiLeWangAssistanceUsers.get(i);
                            if(null != xiLeWangAssistanceUser){
                                XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                                xiLeWangIncomeReport.setType(1);
                                xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                                xiLeWangIncomeReport.setOpenid(xiLeWangAssistanceUser.getOpenid());
                                xiLeWangIncomeReport.setJdOrderId(xiLeWangJdOrderSkuInfo.getJdOrderId());
                                xiLeWangIncomeReport.setJdOrderSkuId(xiLeWangJdOrderSkuInfo.getSkuId());
                                xiLeWangIncomeReport.setJdOrderSkuIndex(xiLeWangJdOrderSkuInfo.getSkuIndex());
                                xiLeWangIncomeReport.setMoney(rebate.multiply(xiLeWangAssistanceUser.getRewardRatio()).divide(BigDecimal.valueOf(100L)));
                                XiLeWangIncomeReport temp =
                                        xiLeWangIncomeReportService.selectByProperty(xiLeWangIncomeReport.getType(), xiLeWangIncomeReport.getOpenid(),xiLeWangIncomeReport.getJdOrderId(),xiLeWangIncomeReport.getJdOrderSkuIndex());
                                if(null == temp){
                                    xiLeWangIncomeReport.setId(IdGenerator.nextId());
                                    xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                                }else{
                                    xiLeWangIncomeReport.setId(temp.getId());
                                    xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                                }
                            }
                        }
                    }
                    XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(xiLeWangOrder.getOpenid());
                    boolean hasMaster = null != xiLeWangUser && !StringUtils.isNullOrEmpty(xiLeWangUser.getMasterOpenid());
                    if(hasMaster){
                        XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                        xiLeWangIncomeReport.setType(2);
                        xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                        xiLeWangIncomeReport.setOpenid(xiLeWangUser.getMasterOpenid());
                        xiLeWangIncomeReport.setJdOrderId(xiLeWangJdOrderSkuInfo.getJdOrderId());
                        xiLeWangIncomeReport.setJdOrderSkuId(xiLeWangJdOrderSkuInfo.getSkuId());
                        xiLeWangIncomeReport.setJdOrderSkuIndex(xiLeWangJdOrderSkuInfo.getSkuIndex());
                        xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice());
                        XiLeWangIncomeReport temp =
                                xiLeWangIncomeReportService.selectByProperty(xiLeWangIncomeReport.getType(), xiLeWangIncomeReport.getOpenid(),xiLeWangIncomeReport.getJdOrderId(),xiLeWangIncomeReport.getJdOrderSkuIndex());
                        if(null == temp){
                            xiLeWangIncomeReport.setId(IdGenerator.nextId());
                            xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                        }else{
                            xiLeWangIncomeReport.setId(temp.getId());
                            xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                        }
                    }
                }
                XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                xiLeWangIncomeReport.setType(0);
                xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                xiLeWangIncomeReport.setOpenid(xiLeWangOrder.getOpenid());
                xiLeWangIncomeReport.setJdOrderId(xiLeWangJdOrderSkuInfo.getJdOrderId());
                xiLeWangIncomeReport.setJdOrderSkuId(xiLeWangJdOrderSkuInfo.getSkuId());
                xiLeWangIncomeReport.setJdOrderSkuIndex(xiLeWangJdOrderSkuInfo.getSkuIndex());
                xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice());
                int result = 0;
                XiLeWangIncomeReport xiLeWangIncomeReportTemp =
                        xiLeWangIncomeReportService.selectByProperty(xiLeWangIncomeReport.getType(), xiLeWangIncomeReport.getOpenid(),xiLeWangIncomeReport.getJdOrderId(),xiLeWangIncomeReport.getJdOrderSkuIndex());
                if(null == xiLeWangIncomeReportTemp){
                    xiLeWangIncomeReport.setId(IdGenerator.nextId());
                    result = xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                }else{
                    xiLeWangIncomeReport.setId(xiLeWangIncomeReportTemp.getId());
                    result = xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                }
                if(result > 0){
                    XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfoTemp = new XiLeWangJdOrderSkuInfo();
                    xiLeWangJdOrderSkuInfoTemp.setId(skuInfoId);
                    xiLeWangJdOrderSkuInfoTemp.setState(2);
                    result = xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfoTemp);
                    if(result > 0 && xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                        // 发送消息存余额
                        amqpTemplate.convertAndSend("quartz_balance_save",xiLeWangJdOrderSkuInfo.getJdOrderId());
                    }
                }
            }
        }
    }

    @RabbitListener(queues = "quartz_balance_save")
    public void quartzBalanceSave(Long jdOrderId){
        List<XiLeWangIncomeReport> xiLeWangIncomeReports = xiLeWangIncomeReportService.selectByJdOrderId(jdOrderId);
        if(!CollectionUtils.isNullOrEmpty(xiLeWangIncomeReports)){
            for(XiLeWangIncomeReport xiLeWangIncomeReport : xiLeWangIncomeReports){
                if(null != xiLeWangIncomeReport && xiLeWangIncomeReport.getState() < 1){
                    int result = xiLeWangUserService.updateMoneyByPrimaryKey(xiLeWangIncomeReport.getType(),xiLeWangIncomeReport.getMoney(),xiLeWangIncomeReport.getOpenid());
                    if(result > 0){
                        XiLeWangIncomeReport temp = new XiLeWangIncomeReport();
                        temp.setId(xiLeWangIncomeReport.getId());
                        temp.setState(1);
                        xiLeWangIncomeReportService.updateByPrimaryKeySelective(temp);
                    }
                }
            }
        }
    }
}
