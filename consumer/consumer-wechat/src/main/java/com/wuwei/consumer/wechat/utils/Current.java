package com.wuwei.consumer.wechat.utils;

import com.wuwei.base.utils.SessionKey;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;

public class Current {

    public static String getOpenid(){
        HttpSession session = (HttpSession) RequestContextHolder.getRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
        return (String)session.getAttribute(SessionKey.OPENID);
    }
}
