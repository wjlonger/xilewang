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

    private Long skuInfoId;

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

    public Long getSkuInfoId() {
        return skuInfoId;
    }

    public void setSkuInfoId(Long skuInfoId) {
        this.skuInfoId = skuInfoId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        if(null == money){
            this.money = money;
        } else if(money.compareTo(BigDecimal.valueOf(0L)) == 1 && money.compareTo(BigDecimal.valueOf(0.01)) <= 0){
            // 大于0 小于等于0.01
            this.money = BigDecimal.valueOf(0.01);
        }else if(money.compareTo(BigDecimal.valueOf(0L)) <= 0){
            // 小于等于0
            this.money = BigDecimal.valueOf(0L);
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