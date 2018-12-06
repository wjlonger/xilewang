package com.wuwei.consumer.quartz.hystric;

import com.wuwei.consumer.quartz.service.JdOrderService;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdOrderServiceHystric implements JdOrderService {

    @Override
    public UnionOpenOrderQueryResponse query(OrderReq orderReq) {
        return null;
    }

}
