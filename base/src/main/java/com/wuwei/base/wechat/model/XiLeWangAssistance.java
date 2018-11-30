package com.wuwei.base.wechat.model;

import java.math.BigDecimal;
import java.util.Date;

public class XiLeWangAssistance {
    private Long id;

    private String openid;

    private Long skuId;

    private BigDecimal initialRatio;

    private Date gmtCreate;

    private Date gmtModified;

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

    public BigDecimal getInitialRatio() {
        return initialRatio;
    }

    public void setInitialRatio(BigDecimal initialRatio) {
        this.initialRatio = initialRatio;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}