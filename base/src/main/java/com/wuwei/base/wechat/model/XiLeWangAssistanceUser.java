package com.wuwei.base.wechat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class XiLeWangAssistanceUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String openid;

    private String nickName;

    private String avatarUrl;

    private BigDecimal assistanceRatio;

    private BigDecimal rewardRatio;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public BigDecimal getAssistanceRatio() {
        return assistanceRatio;
    }

    public void setAssistanceRatio(BigDecimal assistanceRatio) {
        this.assistanceRatio = assistanceRatio;
    }

    public BigDecimal getRewardRatio() {
        return rewardRatio;
    }

    public void setRewardRatio(BigDecimal rewardRatio) {
        this.rewardRatio = rewardRatio;
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