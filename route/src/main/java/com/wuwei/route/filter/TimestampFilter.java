package com.wuwei.route.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimestampFilter extends ZuulFilter {

    private static final long MAX_DIFF = 600000L;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");
        String timestampStr = request.getParameter("timestamp");
        if(StringUtils.isEmpty(timestampStr)){

            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"读取不到时间戳！\"}");
            ctx.set("isSuccess", false);
            return null;
        }
        long currentTimestamp =  System.currentTimeMillis();
        long timestamp = Long.parseLong(timestampStr);
        long diff = currentTimestamp - timestamp;
        if(diff < -MAX_DIFF || diff > MAX_DIFF ){
            response.setContentType("application/json");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"msg\":\"已过请求的有效期！\"}");
            ctx.set("isSuccess", false);
            return null;
        }
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        System.out.println(1);
        return null;
    }
}
