package com.wuwei.route.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wuwei.base.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class SignFilter extends ZuulFilter {

    private static final String API_SECRET_KEY = "XILEWANGDESIGNBYWUJUNLONG";

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (Boolean) ctx.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");

        String signStr = request.getParameter("sign");
        if(StringUtils.isEmpty(signStr)){
            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"读取数字签名错误！\"}");
            ctx.set("isSuccess", false);
            return null;
        }

        String timestampStr = request.getParameter("timestamp");
        String uniqueCodeStr = request.getParameter("uniquecode");
        String sign = MD5.MD5Encode(timestampStr + API_SECRET_KEY + uniqueCodeStr);
        if(!signStr.equals(sign)){
            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"数字签名校验错误\"}");
            ctx.set("isSuccess", false);
            return null;
        }
        if(stringRedisTemplate.hasKey(sign)){
            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"数字签名重复使用\"}");
            ctx.set("isSuccess", false);
            return null;
        }
        stringRedisTemplate.opsForValue().set(sign,sign,15, TimeUnit.MINUTES);
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        return null;
    }
}
