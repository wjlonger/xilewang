package com.wuwei.base.jd.service;

import com.wuwei.base.jd.model.GoodsSearch;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;

public interface HomeService {

    String slideShow();

    UnionOpenGoodsQueryResponse explosiveGoods(GoodsSearch goodsSearch);

}
