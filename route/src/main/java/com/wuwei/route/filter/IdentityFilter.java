package com.wuwei.route.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wuwei.base.util.SessionKey;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IdentityFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if((boolean) ctx.get("isSuccess")){
            HttpServletRequest request = ctx.getRequest();
            return !request.getRequestURI().startsWith("/api/wechat/xilewang/user/code2Session");
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        cookieLable:{
            if(null != cookies && cookies.length > 0){
                for(Cookie cookie : cookies){
                    if("SESSION".equals(cookie.getName())){
                        if(!StringUtils.isEmpty(cookie.getValue())){
                            break cookieLable;
                        }
                    }
                }
            }
            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"未找到登录信息\"}");
            ctx.set("isSuccess", false);
            return null;
        }
        String openid = (String)session.getAttribute(SessionKey.OPENID);
        if(StringUtils.isEmpty(openid)){
            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"登录验证失败\"}");
            ctx.set("isSuccess", false);
            return null;
        }
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        return null;
    }
}
