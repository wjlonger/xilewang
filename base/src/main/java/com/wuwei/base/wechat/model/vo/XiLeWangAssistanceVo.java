package com.wuwei.base.wechat.model.vo;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import jd.union.open.goods.query.response.GoodsResp;

import java.util.List;

public class XiLeWangAssistanceVo extends XiLeWangAssistance {

    private GoodsResp goodsResp;

    private List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers;

    public List<XiLeWangAssistanceUser> getXiLeWangAssistanceUsers() {
        return xiLeWangAssistanceUsers;
    }

    public void setXiLeWangAssistanceUsers(List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers) {
        this.xiLeWangAssistanceUsers = xiLeWangAssistanceUsers;
    }

    public GoodsResp getGoodsResp() {
        return goodsResp;
    }

    public void setGoodsResp(GoodsResp goodsResp) {
        this.goodsResp = goodsResp;
    }
}