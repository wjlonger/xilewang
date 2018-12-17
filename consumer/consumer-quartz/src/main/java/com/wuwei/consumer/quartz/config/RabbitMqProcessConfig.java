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

    @Value("${goods.ratio}")
    private BigDecimal ratio;

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
                                        xiLeWangJdOrderSkuInfo.setState(0);
                                        // 将钱归零，交由消息队重新进行计算
                                        xiLeWangJdOrderSkuInfo.setRebatePrice(BigDecimal.valueOf(0L));
                                        // sku index
                                        xiLeWangJdOrderSkuInfo.setSkuIndex(i);
                                        int result;
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
                                        if(result > 0){
                                            // 发消息处理金额
                                            amqpTemplate.convertAndSend("quartz_sku_rebate_price",xiLeWangJdOrderSkuInfo);
                                        }
                                    }
                                }
                            }
                            if(null == xiLeWangJdOrderService.selectByPrimaryKey(xiLeWangJdOrder.getOrderId())){
                                xiLeWangJdOrder.setOpenid(openid);
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
            int result;
            XiLeWangJdOrderSkuInfo temp = new XiLeWangJdOrderSkuInfo();
            temp.setId(xiLeWangJdOrderSkuInfo.getId());
            // validCode详见 https://media.jd.com/jhtml/page/apidetail/apidetail.html
            //region 无效订单
            if(xiLeWangJdOrderSkuInfo.getValidCode() < 15){

                temp.setState(-1);
                temp.setRebatePrice(BigDecimal.valueOf(0L));
            }
            //endregion

            //region 有效订单
            else {
                temp.setState(1);
                // 预估佣金
                BigDecimal rebate = xiLeWangJdOrderSkuInfo.getEstimateFee();
                // 18为已结算
                if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                    // 实际佣金
                    rebate = xiLeWangJdOrderSkuInfo.getActualFee();
                }
                if(BigDecimal.valueOf(0L).compareTo(rebate) >= 0){
                    // 返利小于等于0
                    temp.setRebatePrice(BigDecimal.valueOf(0L));
                } else {
                    // 返利大于0
                    BigDecimal ratio = BigDecimal.valueOf(0L);
                    Long orderId = Long.parseLong(xiLeWangJdOrderSkuInfo.getSubUnionId());
                    XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderId);
                    if(null != xiLeWangOrder){
                        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(xiLeWangOrder.getAssistanceId());
                        if(null != xiLeWangAssistance){
                            ratio = ratio.add(xiLeWangAssistance.getInitialRatio());
                            if(xiLeWangOrder.getAssistanceId() > 0){
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
                        }else{
                            ratio = ratio.add(xiLeWangOrder.getInitialRatio());
                        }
                    }else{
                        ratio = this.ratio;
                    }
                    temp.setRebatePrice(rebate.multiply(ratio).divide(BigDecimal.valueOf(100L)));
                }
            }
            //endregion
            result = xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(temp);
            if(result > 0){
                // 有没有佣金都要去写入明细
                amqpTemplate.convertAndSend("quartz_income_report_save",temp.getId());
            }
        }
    }

    @RabbitListener(queues = "quartz_income_report_save")
    public void quartzIncomeReportSave(Long skuInfoId){
        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = xiLeWangJdOrderSkuInfoService.selectByPrimaryKey(skuInfoId);
        if(null != xiLeWangJdOrderSkuInfo){

            //region 无效订单
            if(xiLeWangJdOrderSkuInfo.getValidCode() < 15){
                List<XiLeWangIncomeReport> xiLeWangIncomeReports =  xiLeWangIncomeReportService.selectBySkuInfoId(skuInfoId);
                if(!CollectionUtils.isNullOrEmpty(xiLeWangIncomeReports)){
                    for(XiLeWangIncomeReport xiLeWangIncomeReport : xiLeWangIncomeReports){
                        if(null != xiLeWangIncomeReport){
                            XiLeWangIncomeReport temp = new XiLeWangIncomeReport();
                            temp.setId(xiLeWangIncomeReport.getId());
                            temp.setState(-1);
                            temp.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                            xiLeWangIncomeReportService.updateByPrimaryKeySelective(temp);
                        }
                    }
                }
            }
            //endregion

            //region 有效订单
            else {

                //region 返利佣金
                BigDecimal rebate = xiLeWangJdOrderSkuInfo.getEstimateFee();
                // validCode详见 https://media.jd.com/jhtml/page/apidetail/apidetail.html
                // 18为已结算
                if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                    rebate = xiLeWangJdOrderSkuInfo.getActualFee();
                }
                //endregion

                //region 返利大于0
                if(rebate.compareTo(BigDecimal.valueOf(0L)) == 1){
                    Long orderId = Long.parseLong(xiLeWangJdOrderSkuInfo.getSubUnionId());
                    XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderId);
                    if(null != xiLeWangOrder){

                        //region 助力奖励
                        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(xiLeWangOrder.getAssistanceId());
                        if(null != xiLeWangAssistance){
                            List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                            if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                                int length = Math.min(xiLeWangAssistanceUsers.size(),xiLeWangAssistance.getAssistancePeopleNum());
                                for(int i=0; i<length; i++){
                                    XiLeWangAssistanceUser xiLeWangAssistanceUser = xiLeWangAssistanceUsers.get(i);
                                    if(null != xiLeWangAssistanceUser){
                                        XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                                        xiLeWangIncomeReport.setType(1);
                                        xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                                        xiLeWangIncomeReport.setOpenid(xiLeWangAssistanceUser.getOpenid());
                                        xiLeWangIncomeReport.setSkuInfoId(skuInfoId);
                                        xiLeWangIncomeReport.setMoney(rebate.multiply(xiLeWangAssistanceUser.getRewardRatio()).divide(BigDecimal.valueOf(100L)));
                                        xiLeWangIncomeReport.setState(0);
                                        XiLeWangIncomeReport temp =
                                                xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(xiLeWangIncomeReport.getOpenid(),skuInfoId);
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
                        }
                        //endregion

                        //region 师傅奖励
                        XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(xiLeWangOrder.getOpenid());
                        boolean hasMaster = null != xiLeWangUser && !StringUtils.isNullOrEmpty(xiLeWangUser.getMasterOpenid());
                        if(hasMaster){
                            XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                            xiLeWangIncomeReport.setType(2);
                            xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                            xiLeWangIncomeReport.setOpenid(xiLeWangUser.getMasterOpenid());
                            xiLeWangIncomeReport.setSkuInfoId(skuInfoId);
                            xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice());
                            xiLeWangIncomeReport.setState(0);
                            XiLeWangIncomeReport temp =
                                    xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(xiLeWangIncomeReport.getOpenid(),skuInfoId);
                            if(null == temp){
                                xiLeWangIncomeReport.setId(IdGenerator.nextId());
                                xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                            }else{
                                xiLeWangIncomeReport.setId(temp.getId());
                                xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                            }
                        }
                        //endregion

                        //region 自己的返利
                        XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                        xiLeWangIncomeReport.setType(0);
                        xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                        xiLeWangIncomeReport.setOpenid(xiLeWangOrder.getOpenid());
                        xiLeWangIncomeReport.setSkuInfoId(skuInfoId);
                        xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice());
                        xiLeWangIncomeReport.setState(0);
                        int result;
                        XiLeWangIncomeReport xiLeWangIncomeReportTemp =
                                xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(xiLeWangIncomeReport.getOpenid(),xiLeWangIncomeReport.getSkuInfoId());
                        if(null == xiLeWangIncomeReportTemp){
                            xiLeWangIncomeReport.setId(IdGenerator.nextId());
                            result = xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                        }else{
                            xiLeWangIncomeReport.setId(xiLeWangIncomeReportTemp.getId());
                            result = xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                        }
                        //endregion

                        //region 回写SkuInfo表state字段
                        if(result > 0){
                            XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfoTemp = new XiLeWangJdOrderSkuInfo();
                            xiLeWangJdOrderSkuInfoTemp.setId(skuInfoId);
                            xiLeWangJdOrderSkuInfoTemp.setState(2);
                            result = xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfoTemp);
                            if(result > 0 && xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                                // 发送消息存余额
                                amqpTemplate.convertAndSend("quartz_balance_save",skuInfoId);
                            }
                        }
                        //endregion
                    }
                }
                //endregion

                //region 返利小于等于0
                else{
                    XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfoTemp = new XiLeWangJdOrderSkuInfo();
                    xiLeWangJdOrderSkuInfoTemp.setId(skuInfoId);
                    xiLeWangJdOrderSkuInfoTemp.setState(2);
                    xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfoTemp);
                }
                //endregion

            }
            //endregion
        }
    }

    @RabbitListener(queues = "quartz_balance_save")
    public void quartzBalanceSave(Long skuInfoId){
        List<XiLeWangIncomeReport> xiLeWangIncomeReports = xiLeWangIncomeReportService.selectBySkuInfoId(skuInfoId);
        if(!CollectionUtils.isNullOrEmpty(xiLeWangIncomeReports)){
            for(XiLeWangIncomeReport xiLeWangIncomeReport : xiLeWangIncomeReports){
                if(null != xiLeWangIncomeReport && xiLeWangIncomeReport.getState() == 0 && xiLeWangIncomeReport.getValidCode() == 18){
                    int result = xiLeWangUserService.updateMoneyByPrimaryKey(xiLeWangIncomeReport.getType(),xiLeWangIncomeReport.getMoney().doubleValue(),xiLeWangIncomeReport.getOpenid());
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
