package com.wuwei.base.wechat.model.vo;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.base.wechat.model.XiLeWangJdOrderSkuInfo;

import java.math.BigDecimal;
import java.util.List;

public class XiLeWangJdOrderVo extends XiLeWangJdOrder {

    private List<String> imgs;

    private Integer nums;

    private BigDecimal price;

    private BigDecimal rebate;

    private List<XiLeWangJdOrderSkuInfo> xiLeWangJdOrderSkuInfos;

    public List<XiLeWangJdOrderSkuInfo> getXiLeWangJdOrderSkuInfos() {
        return xiLeWangJdOrderSkuInfos;
    }

    public void setXiLeWangJdOrderSkuInfos(List<XiLeWangJdOrderSkuInfo> xiLeWangJdOrderSkuInfos) {
        this.xiLeWangJdOrderSkuInfos = xiLeWangJdOrderSkuInfos;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }
}
