package com.wuwei.provider.wechat.controller;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.base.wechat.service.XiLeWangUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/user")
public class XiLeWangUserController {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @GetMapping("/code2Session/{code}")
    public String code2session(@PathVariable("code") String code){
        return xiLeWangUserService.code2Session(code);
    }

    @GetMapping("/{openid}")
    public XiLeWangUser selectById(@PathVariable("openid") String openid){
        return xiLeWangUserService.selectByOpenid(openid);
    }

    @PostMapping
    public int insert(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.insert(xiLeWangUser);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.insertSelective(xiLeWangUser);
    }

    @PutMapping
    public int updateById(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.updateByOpenid(xiLeWangUser);
    }

    @PostMapping("/save")
    public int save(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.save(xiLeWangUser);
    }

}
