package com.wuwei.consumer.wechat.aop;


import com.wuwei.base.utils.SessionKey;
import com.wuwei.base.wechat.model.XiLeWangUser;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;

@Aspect
@Configuration
public class WeChatServiceAop {

    @AfterReturning(returning="obj",pointcut="execution(* com.wuwei.consumer.wechat.service.XiLeWangUserService.code2Session(java.lang.String,java.lang.String))")
    public void afterReturningCode2Session(Object obj){
        if(obj != null && obj instanceof String) {
            HttpSession session = (HttpSession) RequestContextHolder.getRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
            session.setAttribute(SessionKey.OPENID,obj.toString());
        }
    }

}
