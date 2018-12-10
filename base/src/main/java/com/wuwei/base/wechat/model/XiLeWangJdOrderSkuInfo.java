package com.wuwei.base.wechat.model;

import jd.union.open.order.query.response.SkuInfo;
import sun.plugin2.message.Serializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class XiLeWangJdOrderSkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public XiLeWangJdOrderSkuInfo(){}

    public XiLeWangJdOrderSkuInfo(SkuInfo skuInfo){
        if(null != skuInfo){
            this.actualCosPrice = new BigDecimal(skuInfo.getActualCosPrice());
            this.actualFee = new BigDecimal(skuInfo.getActualFee());
            this.commissionRate = new BigDecimal(skuInfo.getCommissionRate());
            this.estimateCosPrice = new BigDecimal(skuInfo.getEstimateCosPrice());
            this.estimateFee = new BigDecimal(skuInfo.getEstimateFee());
            this.finalRate = new BigDecimal(skuInfo.getFinalRate());
            this.cid1 = skuInfo.getCid1();
            this.frozenSkuNum = skuInfo.getFrozenSkuNum();
            this.price = new BigDecimal(skuInfo.getPrice());
            this.cid2 = skuInfo.getCid2();
            this.skuId = skuInfo.getSkuId();
            this.skuName = skuInfo.getSkuName();
            this.skuNum = skuInfo.getSkuNum();
            this.skuReturnNum = skuInfo.getSkuReturnNum();
            this.subSideRate = new BigDecimal(skuInfo.getSubSideRate());
            this.subsidyRate = new BigDecimal(skuInfo.getSubsidyRate());
            this.cid3 = skuInfo.getCid3();
            this.validCode = skuInfo.getValidCode();
            this.subUnionId = skuInfo.getSubUnionId();
        }
    }

    private Long id;

    private BigDecimal actualCosPrice;

    private BigDecimal actualFee;

    private BigDecimal commissionRate;

    private BigDecimal estimateCosPrice;

    private BigDecimal estimateFee;

    private BigDecimal finalRate;

    private Long cid1;

    private Long frozenSkuNum;

    private BigDecimal price;

    private Long cid2;

    private Long skuId;

    private String skuName;

    private Long skuNum;

    private Long skuReturnNum;

    private BigDecimal subSideRate;

    private BigDecimal subsidyRate;

    private Long cid3;

    private Integer validCode;

    private String subUnionId;

    private String img;

    private BigDecimal rebatePrice;

    private Long jdOrderId;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getActualCosPrice() {
        return actualCosPrice;
    }

    public void setActualCosPrice(BigDecimal actualCosPrice) {
        this.actualCosPrice = actualCosPrice;
    }

    public BigDecimal getActualFee() {
        return actualFee;
    }

    public void setActualFee(BigDecimal actualFee) {
        this.actualFee = actualFee;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getEstimateCosPrice() {
        return estimateCosPrice;
    }

    public void setEstimateCosPrice(BigDecimal estimateCosPrice) {
        this.estimateCosPrice = estimateCosPrice;
    }

    public BigDecimal getEstimateFee() {
        return estimateFee;
    }

    public void setEstimateFee(BigDecimal estimateFee) {
        this.estimateFee = estimateFee;
    }

    public BigDecimal getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(BigDecimal finalRate) {
        this.finalRate = finalRate;
    }

    public Long getCid1() {
        return cid1;
    }

    public void setCid1(Long cid1) {
        this.cid1 = cid1;
    }

    public Long getFrozenSkuNum() {
        return frozenSkuNum;
    }

    public void setFrozenSkuNum(Long frozenSkuNum) {
        this.frozenSkuNum = frozenSkuNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCid2() {
        return cid2;
    }

    public void setCid2(Long cid2) {
        this.cid2 = cid2;
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

    public Long getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Long skuNum) {
        this.skuNum = skuNum;
    }

    public Long getSkuReturnNum() {
        return skuReturnNum;
    }

    public void setSkuReturnNum(Long skuReturnNum) {
        this.skuReturnNum = skuReturnNum;
    }

    public BigDecimal getSubSideRate() {
        return subSideRate;
    }

    public void setSubSideRate(BigDecimal subSideRate) {
        this.subSideRate = subSideRate;
    }

    public BigDecimal getSubsidyRate() {
        return subsidyRate;
    }

    public void setSubsidyRate(BigDecimal subsidyRate) {
        this.subsidyRate = subsidyRate;
    }

    public Long getCid3() {
        return cid3;
    }

    public void setCid3(Long cid3) {
        this.cid3 = cid3;
    }

    public Integer getValidCode() {
        return validCode;
    }

    public void setValidCode(Integer validCode) {
        this.validCode = validCode;
    }

    public String getSubUnionId() {
        return subUnionId;
    }

    public void setSubUnionId(String subUnionId) {
        this.subUnionId = subUnionId == null ? null : subUnionId.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public BigDecimal getRebatePrice() {
        return rebatePrice;
    }

    public void setRebatePrice(BigDecimal rebatePrice) {
        this.rebatePrice = rebatePrice;
    }

    public Long getJdOrderId() {
        return jdOrderId;
    }

    public void setJdOrderId(Long jdOrderId) {
        this.jdOrderId = jdOrderId;
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