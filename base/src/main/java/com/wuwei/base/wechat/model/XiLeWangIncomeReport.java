package com.wuwei.base.wechat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class XiLeWangIncomeReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer type;

    private Integer validCode;

    private String openid;

    private Long jdOrderId;

    private Long jdOrderSkuId;

    private Integer jdOrderSkuIndex;

    private BigDecimal money;

    private Integer state;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValidCode() {
        return validCode;
    }

    public void setValidCode(Integer validCode) {
        this.validCode = validCode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Long getJdOrderId() {
        return jdOrderId;
    }

    public void setJdOrderId(Long jdOrderId) {
        this.jdOrderId = jdOrderId;
    }

    public Long getJdOrderSkuId() {
        return jdOrderSkuId;
    }

    public void setJdOrderSkuId(Long jdOrderSkuId) {
        this.jdOrderSkuId = jdOrderSkuId;
    }

    public Integer getJdOrderSkuIndex() {
        return jdOrderSkuIndex;
    }

    public void setJdOrderSkuIndex(Integer jdOrderSkuIndex) {
        this.jdOrderSkuIndex = jdOrderSkuIndex;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        if(null == money || BigDecimal.valueOf(0.01).compareTo(money) <= 0){
            this.money = money;
        }else{
            this.money = BigDecimal.valueOf(0.01);
        }
        if(null == money){
            this.money = money;
        } else if(BigDecimal.valueOf(0L).compareTo(money) == -1 && BigDecimal.valueOf(0.01).compareTo(money) == 1){
            // 大于0 小于0.01
            this.money = BigDecimal.valueOf(0.01);
        } else{
            this.money = money;
        }
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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