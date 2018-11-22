package com.wuwei.consumer.wechat.controller;

import com.wuwei.base.utils.SessionKey;
import com.wuwei.base.wechat.model.WeChatXiLeWang;
import com.wuwei.consumer.wechat.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat")
public class WechatController {

    @Autowired
    private WeChatService weChatService;

    @GetMapping("/code2Session/{code}")
    public String code2Session(@PathVariable("code") String code, HttpServletRequest request){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        WeChatXiLeWang weChatXiLeWang = weChatService.code2Session(code);
        request.getSession().setAttribute(SessionKey.WeChatXiLeWang, weChatXiLeWang);
        return request.getSession().getId();
    }

    @GetMapping("/refreshSession")
    public String refreshSession(HttpServletRequest request){
        return request.getSession().getId();
    }

}

