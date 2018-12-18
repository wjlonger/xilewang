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

                                        //region 获取openid
                                        if(StringUtils.isNullOrEmpty(openid)){
                                            try{
                                                Long orderid = Long.parseLong(skuInfo.getSubUnionId());
                                                XiLeWangOrder xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderid);
                                                if(null != xiLeWangOrder){
                                                    openid = xiLeWangOrder.getOpenid();
                                                }
                                            }catch (NumberFormatException e){

                                            }
                                        }
                                        //endregion

                                        //region 实例化XiLeWangJdOrderSkuInfo
                                        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = new XiLeWangJdOrderSkuInfo(skuInfo);
                                        // 京东订单ID
                                        xiLeWangJdOrderSkuInfo.setJdOrderId(orderResp.getOrderId());
                                        xiLeWangJdOrderSkuInfo.setState(0);
                                        // 将钱归零，交由消息队重新进行计算
                                        xiLeWangJdOrderSkuInfo.setRebatePrice(BigDecimal.valueOf(0L));
                                        // sku index
                                        xiLeWangJdOrderSkuInfo.setSkuIndex(i);
                                        //endregion

                                        //region 查询XiLeWangJdOrderSkuInfo
                                        int result;
                                        XiLeWangJdOrderSkuInfo temp = xiLeWangJdOrderSkuInfoService.selectByOrderIdAndSkuIndex(orderResp.getOrderId(),i);
                                        //endregion

                                        //region 执行插入逻辑
                                        if(null == temp){
                                            xiLeWangJdOrderSkuInfo.setId(IdGenerator.nextId());
                                            //region 获取默认图
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
                                            result = xiLeWangJdOrderSkuInfoService.insertSelective(xiLeWangJdOrderSkuInfo);
                                        }
                                        //endregion

                                        //region 执行更新逻辑
                                        else{
                                            xiLeWangJdOrderSkuInfo.setId(temp.getId());
                                            result = xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfo);
                                        }
                                        //endregion

                                        //region 发消息处理返利
                                        if(result > 0){
                                            amqpTemplate.convertAndSend("quartz_sku_rebate_price",xiLeWangJdOrderSkuInfo);
                                        }
                                        //endregion

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
            XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfoTemp = new XiLeWangJdOrderSkuInfo();
            xiLeWangJdOrderSkuInfoTemp.setId(xiLeWangJdOrderSkuInfo.getId());

            BigDecimal rebate = BigDecimal.valueOf(0L);
            BigDecimal ratio = BigDecimal.valueOf(0L);

            //region 计算返利金额
            rebate = rebate.add(xiLeWangJdOrderSkuInfo.getEstimateFee());
            // 18为已结算
            if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                // 返利取实际佣金
                rebate = xiLeWangJdOrderSkuInfo.getActualFee();
            }
            //endregion

            //region 计算返利比例
            XiLeWangOrder xiLeWangOrder = null;
            try{
                Long orderId = Long.parseLong(xiLeWangJdOrderSkuInfo.getSubUnionId());
                xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderId);
            }catch (NumberFormatException e){
            }
            if(null != xiLeWangOrder){
                ratio = ratio.add(xiLeWangOrder.getInitialRatio());
                if(xiLeWangJdOrderSkuInfo.getSkuId().equals(xiLeWangOrder.getSkuId())){
                    if(null != xiLeWangOrder.getAssistanceId() && xiLeWangOrder.getAssistanceId() > 0){
                        List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                        if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                            for(XiLeWangAssistanceUser xiLeWangAssistanceUser : xiLeWangAssistanceUsers){
                                if(null != xiLeWangAssistanceUser){
                                    ratio = ratio.add(xiLeWangAssistanceUser.getAssistanceRatio());
                                }
                            }
                        }
                    }
                }
            }
            //endregion

            xiLeWangJdOrderSkuInfoTemp.setRebatePrice(rebate.multiply(ratio).divide(BigDecimal.valueOf(100L)));
            if(xiLeWangJdOrderSkuInfo.getValidCode() < 15){
                xiLeWangJdOrderSkuInfoTemp.setState(-1);
                if(xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfoTemp) > 0){
                    amqpTemplate.convertAndSend("quartz_income_report_invalid_save",xiLeWangJdOrderSkuInfoTemp.getId());
                }
            }else{
                xiLeWangJdOrderSkuInfoTemp.setState(1);
                if(xiLeWangJdOrderSkuInfoService.updateByPrimaryKeySelective(xiLeWangJdOrderSkuInfoTemp) > 0){
                    amqpTemplate.convertAndSend("quartz_income_report_valid_save",xiLeWangJdOrderSkuInfoTemp.getId());
                }
            }
        }
    }

    @RabbitListener(queues = "quartz_income_report_invalid_save")
    public void quartzIncomeReportInvalidSave(Long skuInfoId){
        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = xiLeWangJdOrderSkuInfoService.selectByPrimaryKey(skuInfoId);
        if(null != xiLeWangJdOrderSkuInfo){
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
    }

    @RabbitListener(queues = "quartz_income_report_valid_save")
    public void quartzIncomeReportValidSave(Long skuInfoId){
        XiLeWangJdOrderSkuInfo xiLeWangJdOrderSkuInfo = xiLeWangJdOrderSkuInfoService.selectByPrimaryKey(skuInfoId);
        if(null != xiLeWangJdOrderSkuInfo){
            XiLeWangOrder xiLeWangOrder = null;
            try{
                Long orderId = Long.parseLong(xiLeWangJdOrderSkuInfo.getSubUnionId());
                xiLeWangOrder = xiLeWangOrderService.selectByPrimaryKey(orderId);
            }catch (NumberFormatException e){
            }
            if(null != xiLeWangOrder){

                //region 计算助力奖励
                if(xiLeWangJdOrderSkuInfo.getSkuId().equals(xiLeWangOrder.getSkuId())){
                    if(null != xiLeWangOrder.getAssistanceId() && xiLeWangOrder.getAssistanceId() > 0){
                        List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangOrder.getAssistanceId());
                        if(!CollectionUtils.isNullOrEmpty(xiLeWangAssistanceUsers)){
                            //region 返利金额
                            BigDecimal rebate = xiLeWangJdOrderSkuInfo.getEstimateFee();
                            // 18为已结算
                            if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                                // 返利取实际佣金
                                rebate = xiLeWangJdOrderSkuInfo.getActualFee();
                            }
                            //endregion
                            for(XiLeWangAssistanceUser xiLeWangAssistanceUser : xiLeWangAssistanceUsers){
                                if(null != xiLeWangAssistanceUser){
                                    XiLeWangIncomeReport xiLeWangIncomeReport = new XiLeWangIncomeReport();
                                    xiLeWangIncomeReport.setType(1);
                                    xiLeWangIncomeReport.setValidCode(xiLeWangJdOrderSkuInfo.getValidCode());
                                    xiLeWangIncomeReport.setOpenid(xiLeWangAssistanceUser.getOpenid());
                                    xiLeWangIncomeReport.setSkuInfoId(skuInfoId);
                                    xiLeWangIncomeReport.setMoney(rebate.multiply(xiLeWangAssistanceUser.getRewardRatio()).divide(BigDecimal.valueOf(100L)));
                                    xiLeWangIncomeReport.setState(0);
                                    int result;
                                    XiLeWangIncomeReport temp =
                                            xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(xiLeWangIncomeReport.getOpenid(),skuInfoId);
                                    if(null == temp){
                                        xiLeWangIncomeReport.setId(IdGenerator.nextId());
                                        result = xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                                    }else{
                                        xiLeWangIncomeReport.setId(temp.getId());
                                        result = xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                                    }
                                    if(result > 0 && xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                                        amqpTemplate.convertAndSend("quartz_balance_save",xiLeWangIncomeReport.getId());
                                    }
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
                    xiLeWangIncomeReport.setMoney(xiLeWangJdOrderSkuInfo.getRebatePrice().multiply(this.masterRatio).divide(BigDecimal.valueOf(100L)));
                    xiLeWangIncomeReport.setState(0);
                    int result;
                    XiLeWangIncomeReport temp =
                            xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(xiLeWangIncomeReport.getOpenid(),skuInfoId);
                    if(null == temp){
                        xiLeWangIncomeReport.setId(IdGenerator.nextId());
                        result = xiLeWangIncomeReportService.insertSelective(xiLeWangIncomeReport);
                    }else{
                        xiLeWangIncomeReport.setId(temp.getId());
                        result = xiLeWangIncomeReportService.updateByPrimaryKeySelective(xiLeWangIncomeReport);
                    }
                    if(result > 0 && xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                        amqpTemplate.convertAndSend("quartz_balance_save",xiLeWangIncomeReport.getId());
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
                        xiLeWangIncomeReportService.selectByOpenidAndSkuInfoId(xiLeWangOrder.getOpenid(),skuInfoId);
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
                    if(result > 0){
                        if(xiLeWangJdOrderSkuInfo.getValidCode() == 18){
                            amqpTemplate.convertAndSend("quartz_balance_save",xiLeWangIncomeReport.getId());
                        }
                    }
                }
                //endregion
            }

        }
    }

    @RabbitListener(queues = "quartz_balance_save")
    public void quartzBalanceSave(Long incomeReportId){
        XiLeWangIncomeReport xiLeWangIncomeReport = xiLeWangIncomeReportService.selectByPrimaryKey(incomeReportId);
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
