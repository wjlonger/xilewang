package com.wuwei.consumer.wechat.controller;

import com.wuwei.base.utils.SessionKey;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/user")
public class XiLeWangUserController {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @GetMapping("/code2Session/{code}")
    public String code2Session(@PathVariable("code") String code, HttpServletRequest request){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        xiLeWangUserService.code2Session(code);
        return request.getSession().getId();
    }

    @GetMapping("/refreshSession")
    public String refreshSession(HttpServletRequest request){
        return request.getSession().getId();
    }

    @PostMapping("/save")
    public int save(@RequestBody XiLeWangUser xiLeWangUser, HttpServletRequest request) {

        return xiLeWangUserService.save(xiLeWangUser);
    }

}
