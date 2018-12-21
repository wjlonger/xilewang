package com.wuwei.base.wechat.model.vo;

import com.wuwei.base.wechat.model.XiLeWangFavorite;
import jd.union.open.goods.query.response.GoodsResp;

public class XiLeWangFavoriteVo extends XiLeWangFavorite {

    private GoodsResp goodsResp;

    public GoodsResp getGoodsResp() {
        return goodsResp;
    }

    public void setGoodsResp(GoodsResp goodsResp) {
        this.goodsResp = goodsResp;
    }
}