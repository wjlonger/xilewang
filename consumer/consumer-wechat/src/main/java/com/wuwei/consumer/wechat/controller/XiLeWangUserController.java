package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangIncomeReportService;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/user")
public class XiLeWangUserController {

    JSONObject jsonObject = new JSONObject();

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @Autowired
    private XiLeWangIncomeReportService xiLeWangIncomeReportService;

    @GetMapping("/code2Session/{code}")
    public String code2Session(@PathVariable("code") String code, @RequestParam(name = "inviteCode", required = false) String inviteCode){
        return xiLeWangUserService.code2Session(code, inviteCode);
    }

    @GetMapping("/income")
    public JSONObject income(){
        XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(Current.getOpenid());
        if(null != xiLeWangUser){
            jsonObject.put("balance",xiLeWangUser.getMoney());
            jsonObject.put("total",xiLeWangUser.getRebateMoney().add(xiLeWangUser.getAssistanceMoney()).add(xiLeWangUser.getMasterMoney()));
            Double pending = xiLeWangIncomeReportService.pending(Current.getOpenid());
            jsonObject.put("pending",pending);
        }else{
            jsonObject.put("balance",0);
            jsonObject.put("total",0);
            jsonObject.put("pending",0);
        }
        return jsonObject;
    }

    @GetMapping("/refreshSession")
    public void refreshSession(){ }

    @PostMapping("/save")
    public int save(@RequestBody XiLeWangUser xiLeWangUser) {
        xiLeWangUser.setOpenid(Current.getOpenid());
        return xiLeWangUserService.save(xiLeWangUser);
    }

}
