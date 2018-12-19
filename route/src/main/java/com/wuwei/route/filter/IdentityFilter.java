package com.wuwei.route.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wuwei.base.util.SessionKey;
import org.springframework.util.StringUtils;

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
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        return StringUtils.isEmpty(uri) || !request.getRequestURI().startsWith("/api/wechat/xilewang/user/code2Session");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        HttpSession session = request.getSession();
        String openid = (String)session.getAttribute(SessionKey.OPENID);
        response.setCharacterEncoding("UTF-8");
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
