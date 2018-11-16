package com.wuwei.base.wechat.model;

import java.io.Serializable;

public class WeChatXiLeWang implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openid;

    private String sessionkey;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey == null ? null : sessionkey.trim();
    }
}