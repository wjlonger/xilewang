package com.wuwei.provider.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/accesstoken")
public class XiLeWangWeChatAccessTokenController {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public String getAccessToken(){
        return stringRedisTemplate.opsForValue().get("access_token");
    }

}
