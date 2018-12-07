package com.wuwei.provider.jd.service.test;

import com.jd.open.api.sdk.JdException;
import com.wuwei.base.jd.service.OrderService;
import com.wuwei.base.util.DateUtils;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.request.UnionOpenGoodsQueryRequest;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import jd.union.open.order.query.request.OrderReq;
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
        OrderReq orderReq = new OrderReq();
        orderReq.setPageNo(1);
        orderReq.setPageSize(100);
        //DateUtils.getOrderTimeForJd()
        orderReq.setTime("2018112008");
        orderReq.setType(1);
        UnionOpenOrderQueryResponse response = orderService.query(orderReq);
        OrderResp resps = response.getData()[0];
        SkuInfo skuInfo = resps.getSkuList()[0];

    }
}
