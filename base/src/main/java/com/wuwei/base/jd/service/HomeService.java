package com.wuwei.base.jd.service;

import jd.union.open.goods.query.request.GoodsReq;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;

public interface HomeService {

    String slideShow();

    UnionOpenGoodsQueryResponse explosiveGoods(GoodsReq goodsReq);

}
