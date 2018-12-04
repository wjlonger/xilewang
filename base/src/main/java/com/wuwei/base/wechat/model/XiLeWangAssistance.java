package com.wuwei.base.wechat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class XiLeWangAssistance implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String openid;

    private Long skuId;

    private BigDecimal initialRatio;

    private String assistanceRatio;

    private Integer assistancePeopleNum;

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

    public String getAssistanceRatio() {
        return assistanceRatio;
    }

    public void setAssistanceRatio(String assistanceRatio) {
        this.assistanceRatio = assistanceRatio == null ? null : assistanceRatio.trim();
    }

    public Integer getAssistancePeopleNum() {
        return assistancePeopleNum;
    }

    public void setAssistancePeopleNum(Integer assistancePeopleNum) {
        this.assistancePeopleNum = assistancePeopleNum;
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