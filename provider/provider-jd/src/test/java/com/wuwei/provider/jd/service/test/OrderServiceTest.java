package com.wuwei.provider.jd.service.test;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.service.OrderService;
import com.wuwei.base.util.AES;
import com.wuwei.base.util.DateUtils;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.request.UnionOpenGoodsQueryRequest;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import jd.union.open.order.query.request.OrderReq;
import jd.union.open.order.query.request.UnionOpenOrderQueryRequest;
import jd.union.open.order.query.response.OrderResp;
import jd.union.open.order.query.response.SkuInfo;
import jd.union.open.order.query.response.UnionOpenOrderQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void query() {
        JdClient jdClient = new DefaultJdClient("https://router.jd.com/api",null, "0f7076d0d77949e9a44002773574c53c","156e0e5268c3448da16f66bd3771f1e3");
        OrderReq orderReq = new OrderReq();
        orderReq.setPageNo(1);
        orderReq.setPageSize(100);
        orderReq.setTime("201812111104");
        orderReq.setType(1);
        UnionOpenOrderQueryRequest unionOpenOrderQueryRequest = new UnionOpenOrderQueryRequest();
        unionOpenOrderQueryRequest.setOrderReq(orderReq);
        UnionOpenOrderQueryResponse response = null;
        try {
            response = jdClient.execute(unionOpenOrderQueryRequest);
        } catch (JdException e) {
            e.printStackTrace();
        }
    }
}
