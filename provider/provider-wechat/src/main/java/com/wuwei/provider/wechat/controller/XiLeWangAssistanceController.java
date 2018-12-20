package com.wuwei.provider.wechat.controller;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.vo.XiLeWangAssistanceVo;
import com.wuwei.base.wechat.service.XiLeWangAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/xilewang/assistance")
public class XiLeWangAssistanceController {

    @Autowired
    private XiLeWangAssistanceService xiLeWangAssistanceService;

    @PostMapping
    public int insertSelective(@RequestBody XiLeWangAssistance xiLeWangAssistance){
        return xiLeWangAssistanceService.insertSelective(xiLeWangAssistance);
    }

    @GetMapping("/{id}")
    public XiLeWangAssistance selectByPrimaryKey(@PathVariable("id") Long id){
        return xiLeWangAssistanceService.selectByPrimaryKey(id);
    }

    @PutMapping
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangAssistance xiLeWangAssistance){
        return xiLeWangAssistanceService.updateByPrimaryKeySelective(xiLeWangAssistance);
    }

    @GetMapping("/selectByOpenIdAndSkuId/{openId}/{skuId}")
    public XiLeWangAssistance selectByOpenIdAndSkuId(@PathVariable("openId") String openId, @PathVariable("skuId") Long skuId){
        return xiLeWangAssistanceService.selectByOpenIdAndSkuId(openId,skuId);
    }

    @GetMapping("/selectByOpenIdAndState/{openid}")
    public PageInfo<XiLeWangAssistanceVo> selectByOpenIdAndState(@PathVariable("openid") String openid,@RequestParam(value = "state",required = false) Integer state,
                                                                 @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return this.xiLeWangAssistanceService.selectByOpenIdAndState(openid, state, pageNo, pageSize);
    }

}
