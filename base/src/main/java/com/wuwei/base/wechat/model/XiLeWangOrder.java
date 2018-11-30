package com.wuwei.base.wechat.model;

import java.math.BigDecimal;
import java.util.Date;

public class XiLeWangOrder {
    private Long id;

    private String openid;

    private Long skuId;

    private BigDecimal initialRatio;

    private String url;

    private Long assistanceId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Long assistanceId) {
        this.assistanceId = assistanceId;
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