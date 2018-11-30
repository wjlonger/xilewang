package com.wuwei.base.wechat.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class XiLeWangOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String openid;

    private Long skuId;

    private BigDecimal initialRatio;

    private String url;

    private Long assistanceId;

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
}