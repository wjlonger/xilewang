package com.wuwei.base.wechat.model;

import java.io.Serializable;

public class XiLeWangAssistance implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String openid;

    private Long skuId;

    private String skuName;

    private String images;

    private Double price;

    private Double commission;

    private Double commissionShare;

    private Double ratio;

    private Double assistanceRatio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getCommissionShare() {
        return commissionShare;
    }

    public void setCommissionShare(Double commissionShare) {
        this.commissionShare = commissionShare;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getAssistanceRatio() {
        return assistanceRatio;
    }

    public void setAssistanceRatio(Double assistanceRatio) {
        this.assistanceRatio = assistanceRatio;
    }
}