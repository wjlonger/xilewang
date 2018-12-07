package com.wuwei.consumer.quartz.job;

import com.wuwei.base.util.CollectionUtils;
import com.wuwei.base.util.DateUtils;
import com.wuwei.consumer.quartz.service.JdOrderService;
import com.wuwei.consumer.quartz.service.XiLeWangQuartzService;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.OrderResp;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class GrabOrderJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JdOrderService jdOrderService;

    @Autowired
    private XiLeWangQuartzService xiLeWangQuartzService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        OrderReq orderReq = new OrderReq();
        orderReq.setPageNo(1);
        orderReq.setPageSize(100);
        orderReq.setTime(DateUtils.getOrderTimeForJd());
        UnionOpenOrderQueryResponse response = jdOrderService.query(orderReq);
        if(null != response && !CollectionUtils.isNullOrEmpty(response.getData())){
            OrderResp[] orderResps = response.getData();
            for(OrderResp orderResp : orderResps){
                if(null != orderReq){

                }
            }

        }
        System.out.println(System.currentTimeMillis() + "           运行开始");
        System.out.println(System.currentTimeMillis() + "           运行结束");
    }

}
