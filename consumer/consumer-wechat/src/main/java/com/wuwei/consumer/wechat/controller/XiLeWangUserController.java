package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangIncomeReportService;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @GetMapping("/totalIncome")
    public JSONObject totalIncome(){
        XiLeWangUser xiLeWangUser = this.xiLeWangUserService.selectByPrimaryKey(Current.getOpenid());
        if(null != xiLeWangUser){
            BigDecimal balance =  xiLeWangUser.getMoney();
            if(BigDecimal.valueOf(0L).compareTo(balance) == 0){
                jsonObject.put("balance",0);
            }else{
                jsonObject.put("balance",balance);
            }
            double pending = this.xiLeWangIncomeReportService.totalPending(Current.getOpenid());
            if(pending == 0.0){
                jsonObject.put("pending",0);
            }else{
                jsonObject.put("pending",pending);
            }
            BigDecimal total = xiLeWangUser.getRebateMoney().add(xiLeWangUser.getAssistanceMoney()).add(xiLeWangUser.getMasterMoney());
            if(total.compareTo(BigDecimal.valueOf(0L)) == 0){
                jsonObject.put("total",0);
            }else{
                jsonObject.put("total",total);
            }
            int inviteCount = xiLeWangUserService.inviteCount(Current.getOpenid());
            jsonObject.put("inviteCount",inviteCount);
        }else{
            jsonObject.put("balance",0);
            jsonObject.put("total",0);
            jsonObject.put("pending",0);
            jsonObject.put("inviteCount",0);
        }
        return jsonObject;
    }

    @GetMapping("/inviteIncome")
    public JSONObject inviteIncome(){
        XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(Current.getOpenid());
        if(null != xiLeWangUser){
            BigDecimal totalInviteMoney =  xiLeWangUser.getMasterMoney();
            if(totalInviteMoney.compareTo(BigDecimal.valueOf(0L)) == 0){
                jsonObject.put("totalInviteMoney",0);
            }else{
                jsonObject.put("totalInviteMoney",totalInviteMoney);
            }
            double invitePending = this.xiLeWangIncomeReportService.invitTotal(Current.getOpenid());
            if(invitePending == 0.0){
                jsonObject.put("pendingInviteMoney",0);
            } else {
                jsonObject.put("pendingInviteMoney",invitePending);
            }
            int inviteCount = xiLeWangUserService.inviteCount(Current.getOpenid());
            jsonObject.put("inviteCount",inviteCount);
        }else{
            jsonObject.put("totalInviteMoney",0);
            jsonObject.put("pendingInviteMoney",0);
            jsonObject.put("inviteCount",0);
        }
        return jsonObject;
    }

    @GetMapping("/inviteMember")
    public PageInfo<XiLeWangUser> inviteMember(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return this.xiLeWangUserService.listByMasterOpenid(Current.getOpenid(),pageNo,pageSize);
    }

    @GetMapping("/refreshSession")
    public void refreshSession(){ }

    @PostMapping("/save")
    public int save(@RequestBody XiLeWangUser xiLeWangUser) {
        xiLeWangUser.setOpenid(Current.getOpenid());
        return xiLeWangUserService.save(xiLeWangUser);
    }

}
