package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.util.CollectionUtils;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.XiLeWangAssistanceUser;
import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceService;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceUserService;
import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import com.wuwei.consumer.wechat.service.XiLeWangUserService;
import com.wuwei.consumer.wechat.utils.Current;
import com.wuwei.consumer.wechat.utils.RatioCalculator;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/assistance")
public class XiLeWangAssistanceController {

    @Autowired
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    @Autowired
    private XiLeWangAssistanceUserService xiLeWangAssistanceUserService;

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @Autowired
    private RatioCalculator ratioCalculator;

    /**
     * 固定得到的比例
     */
    @Value("${goods.ratio}")
    private BigDecimal initialRatio;

    /**
     * 助力最高比例
     */
    @Value("${assistance.max.ratio}")
    private int assistanceMaxRatio;

    /**
     * 最多助力人数
     */
    @Value("${assistance.people.number}")
    private int assistancePeopleNum;

    /**
     * 被助力者得到的比例
     */
    @Value("${assistance.ratio}")
    private BigDecimal assistanceRatio;

    /**
     * 助力者得到的比例
     */
    @Value("${reward.ratio}")
    private BigDecimal rewardRatio;

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/{skuId}")
    public JSONObject getAssistanceId(@PathVariable Long skuId) {
        String openId = Current.getOpenid();
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByOpenIdAndSkuId(openId,skuId);
        if(null == xiLeWangAssistance){
            final long id = IdGenerator.nextId();
            jsonObject.put("code",1);
            jsonObject.put("assistance",id);
            jsonObject.put("assistanceUsers",new ArrayList<XiLeWangAssistance>());
        }else{
            List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers= xiLeWangAssistanceUserService.selectByAssistanceId(xiLeWangAssistance.getId());
            jsonObject.put("code",1);
            jsonObject.put("assistance",xiLeWangAssistance.getId());
            jsonObject.put("assistanceUsers",xiLeWangAssistanceUsers);
        }
        return jsonObject;
    }

    @GetMapping("/{skuId}/{assistanceId}")
    public void startAssistance(@PathVariable("skuId") Long skuId,@PathVariable("assistanceId") Long assistanceId){
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(assistanceId);
        if(xiLeWangAssistance == null){
            xiLeWangAssistance = new XiLeWangAssistance();
            xiLeWangAssistance.setId(assistanceId);
            xiLeWangAssistance.setSkuId(skuId);
            xiLeWangAssistance.setOpenid(Current.getOpenid());
            xiLeWangAssistance.setInitialRatio(initialRatio);
            xiLeWangAssistance.setAssistanceRatio(CollectionUtils.join(ratioCalculator.getRatio(assistanceMaxRatio),","));
            xiLeWangAssistance.setAssistancePeopleNum(assistancePeopleNum);
            xiLeWangAssistanceService.insertSelective(xiLeWangAssistance);
        }
    }

    @GetMapping("/detail/{assistanceId}")
    public JSONObject getAssistanceDetail(@PathVariable("assistanceId") Long assistanceId){
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(assistanceId);
        if(null == xiLeWangAssistance){
            jsonObject.put("code",0);
            jsonObject.put("assistance",null);
            jsonObject.put("goods",null);
            jsonObject.put("users",null);

        } else {
            GoodsResp goodsResp = xiLeWangGoodsService.goodsDetail(xiLeWangAssistance.getSkuId());
            List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(assistanceId);
            jsonObject.put("code",1);
            jsonObject.put("assistance",xiLeWangAssistance);
            jsonObject.put("goods",goodsResp);
            jsonObject.put("users",xiLeWangAssistanceUsers);
        }
        return jsonObject;
    }

    @GetMapping("/assistance/{assistanceId}")
    public JSONObject assistance(@PathVariable("assistanceId") Long assistanceId){
        String openId = Current.getOpenid();
        if(StringUtils.isEmpty(openId)){
            jsonObject.put("code",0);
            jsonObject.put("errMsg","登录异常，助力失败");
            return jsonObject;
        }
        XiLeWangUser xiLeWangUser = xiLeWangUserService.selectByPrimaryKey(openId);
        if(null == xiLeWangUser){
            jsonObject.put("code",0);
            jsonObject.put("errMsg","登录异常，助力失败");
            return jsonObject;
        }
        XiLeWangAssistance xiLeWangAssistance = xiLeWangAssistanceService.selectByPrimaryKey(assistanceId);
        if(null == xiLeWangAssistance){
            jsonObject.put("code",0);
            jsonObject.put("errMsg","助力失败~助力信息不存在");
            return jsonObject;
        }
        if(openId.equals(xiLeWangAssistance.getOpenid())){
            jsonObject.put("code",0);
            jsonObject.put("errMsg","自己不可以给自己助力哦~");
            return jsonObject;
        }
        int size = 0;
        List<XiLeWangAssistanceUser> xiLeWangAssistanceUsers = xiLeWangAssistanceUserService.selectByAssistanceId(assistanceId);
        if(null != xiLeWangAssistanceUsers && !xiLeWangAssistanceUsers.isEmpty()){
            size = xiLeWangAssistanceUsers.size();
            if(size >= xiLeWangAssistance.getAssistancePeopleNum()){
                jsonObject.put("code",0);
                jsonObject.put("errMsg","助力已满，爱你么么哒");
                return jsonObject;
            }
            for(XiLeWangAssistanceUser xiLeWangAssistanceUser : xiLeWangAssistanceUsers){
                if(null != xiLeWangAssistance){
                    if(openId.equals(xiLeWangAssistanceUser.getOpenid())){
                        jsonObject.put("code",0);
                        jsonObject.put("errMsg","你已经助力过了哦~");
                        return jsonObject;
                    }
                }
            }
        }
        String[] ratios = xiLeWangAssistance.getAssistanceRatio().split(",");
        if(null == ratios || ratios.length == 0 || ratios.length != xiLeWangAssistance.getAssistancePeopleNum() || ratios.length <= size){
            jsonObject.put("code",0);
            jsonObject.put("errMsg","不能再助力啦~");
            return jsonObject;
        }
        BigDecimal ratio = new BigDecimal(Double.valueOf(ratios[size]));
        XiLeWangAssistanceUser xiLeWangAssistanceUser = new XiLeWangAssistanceUser();
        xiLeWangAssistanceUser.setId(IdGenerator.nextId());
        xiLeWangAssistanceUser.setAssistanceId(xiLeWangAssistance.getId());
        xiLeWangAssistanceUser.setOpenid(xiLeWangUser.getOpenid());
        xiLeWangAssistanceUser.setNickName(xiLeWangUser.getNickName());
        xiLeWangAssistanceUser.setAvatarUrl(xiLeWangUser.getAvatarUrl());
        xiLeWangAssistanceUser.setAssistanceRatio(ratio.multiply(assistanceRatio));
        xiLeWangAssistanceUser.setRewardRatio(ratio.multiply(rewardRatio));
        int i = xiLeWangAssistanceUserService.insert(xiLeWangAssistanceUser);
        if(i <= 0){
            jsonObject.put("code",0);
            jsonObject.put("errMsg","助力失败");
        }else{
            jsonObject.put("code",1);
            jsonObject.put("errMsg","助力成功");
        }
        return jsonObject;
    }

}
