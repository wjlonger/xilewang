package com.wuwei.provider.jd.serviceimpl;

import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.service.OrderService;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.request.UnionOpenOrderQueryRequest;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private JdClient jdClient;

    @Override
    public UnionOpenOrderQueryResponse query(OrderReq orderReq) {
        UnionOpenOrderQueryRequest unionOpenOrderQueryRequest = new UnionOpenOrderQueryRequest();
        if(null == orderReq){
            orderReq = new OrderReq();
        }
        unionOpenOrderQueryRequest.setOrderReq(orderReq);
        UnionOpenOrderQueryResponse response = null;
        try {
            response = jdClient.execute(unionOpenOrderQueryRequest);
        } catch (JdException e) {
            e.printStackTrace();
        }
        return response;
    }
}
