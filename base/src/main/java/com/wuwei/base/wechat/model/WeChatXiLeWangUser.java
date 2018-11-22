package com.wuwei.base.wechat.model;

public class WeChatXiLeWangUser {
    private String openid;

    private Long id;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}