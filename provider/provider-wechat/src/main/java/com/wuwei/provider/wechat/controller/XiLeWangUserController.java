package com.wuwei.provider.wechat.controller;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.base.wechat.service.XiLeWangUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/user")
public class XiLeWangUserController {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @GetMapping("/code2Session/{code}")
    public String code2session(@PathVariable("code") String code,@RequestParam(name = "inviteCode", required = false) String inviteCode){
        return xiLeWangUserService.code2Session(code,inviteCode);
    }

    @GetMapping("/{openid}")
    public XiLeWangUser selectById(@PathVariable("openid") String openid){
        return xiLeWangUserService.selectByPrimaryKey(openid);
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
    public int updateByPrimaryKey(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.updateByPrimaryKey(xiLeWangUser);
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.updateByPrimaryKeySelective(xiLeWangUser);
    }

    @PostMapping("/save")
    public int save(@RequestBody XiLeWangUser xiLeWangUser){
        return xiLeWangUserService.save(xiLeWangUser);
    }

    @PostMapping("/updateMoneyByPrimaryKey")
    public int updateMoneyByPrimaryKey(int type, Double modifyMoney, String openid){
        return xiLeWangUserService.updateMoneyByPrimaryKey(type, BigDecimal.valueOf(modifyMoney), openid);
    }

    @GetMapping("/listByMasterOpenid/{openid}")
    public PageInfo<XiLeWangUser> listByMasterOpenid(@PathVariable("openid") String openid, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return this.xiLeWangUserService.listByMasterOpenid(openid,pageNo,pageSize);
    }

    @GetMapping("/inviteCount/{openid}")
    public Integer inviteCount(@PathVariable("openid") String openid){
        return this.xiLeWangUserService.inviteCount(openid);
    }
}
