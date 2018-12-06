package com.wuwei.base.jd.service;

import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;

public interface OrderService {

    UnionOpenOrderQueryResponse query(OrderReq orderReq);

}
