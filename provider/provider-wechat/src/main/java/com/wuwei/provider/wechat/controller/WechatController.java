package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.WeChatXiLeWang;
import com.wuwei.base.wechat.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WeChatService weChatService;

    @GetMapping("/code2Session/{code}")
    public WeChatXiLeWang code2session(@PathVariable("code") String code){
        return weChatService.code2Session(code);
    }

}
