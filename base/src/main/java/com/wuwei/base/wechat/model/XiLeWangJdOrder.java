package com.wuwei.base.wechat.model;

import jd.union.open.order.query.response.OrderResp;

import java.io.Serializable;
import java.util.Date;

public class XiLeWangJdOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    public XiLeWangJdOrder(){}

    public XiLeWangJdOrder(OrderResp orderResp){
        if(null != orderResp){
            this.orderId = orderResp.getOrderId();
            this.finishTime = orderResp.getFinishTime();
            this.orderEmt = orderResp.getOrderEmt();
            this.orderTime = orderResp.getOrderTime();
            this.parentId = orderResp.getParentId();
            this.payMonth = orderResp.getPayMonth();
            this.plus = orderResp.getPlus();
            this.popId = orderResp.getPopId();
            this.unionId = orderResp.getUnionId();
            this.ext1 = orderResp.getExt1();
            this.validCode = orderResp.getValidCode();
        }
    }

    private Long orderId;

    private Long finishTime;

    private Integer orderEmt;

    private Long orderTime;

    private Long parentId;

    private String payMonth;

    private Integer plus;

    private Long popId;

    private Long unionId;

    private String ext1;

    private Integer validCode;

    private String openid;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getOrderEmt() {
        return orderEmt;
    }

    public void setOrderEmt(Integer orderEmt) {
        this.orderEmt = orderEmt;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth == null ? null : payMonth.trim();
    }

    public Integer getPlus() {
        return plus;
    }

    public void setPlus(Integer plus) {
        this.plus = plus;
    }

    public Long getPopId() {
        return popId;
    }

    public void setPopId(Long popId) {
        this.popId = popId;
    }

    public Long getUnionId() {
        return unionId;
    }

    public void setUnionId(Long unionId) {
        this.unionId = unionId;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
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