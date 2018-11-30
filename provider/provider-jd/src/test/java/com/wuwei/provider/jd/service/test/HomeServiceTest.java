package com.wuwei.provider.jd.service.test;

import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.request.UnionOpenGoodsQueryRequest;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeServiceTest {

    @Autowired
    private JdClient jdClient;

    @Test
    public void explosiveGoods() {
        UnionOpenGoodsQueryRequest request = new UnionOpenGoodsQueryRequest();
        request.setGoodsReqDTO(new GoodsReq());
        UnionOpenGoodsQueryResponse response = null;
        try {
            response = jdClient.execute(request);
            System.out.println(response.getData().toString());
        } catch (JdException e) {
        }
    }
}
