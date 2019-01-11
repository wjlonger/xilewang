package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import com.wuwei.consumer.wechat.utils.Current;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/history")
public class XiLeWangHistoryController {

    private JSONObject json = new JSONObject();

    @Autowired
    private XiLeWangHistoryService xiLeWangHistoryService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/selectByPrimaryKey/{id}")
    public XiLeWangHistory selectByPrimaryKey(@PathVariable("id") Long id){
        return xiLeWangHistoryService.selectByPrimaryKey(id);
    }

    @PostMapping
    public String insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            json.put("code",0);
            json.put("errMsg","保存失败!");
            return json.toJSONString();
        }
        final long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        amqpTemplate.convertAndSend("xilewang_history_insert",xiLeWangHistory);
        json.put("code",1);
        json.put("errMsg","保存成功");
        json.put("id",id);
        return json.toJSONString();
    }

    @PutMapping
    public String updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory || null == xiLeWangHistory.getId()) {
            json.put("code",0);
            json.put("errMsg","更新失败!");
            return json.toJSONString();
        }
        int i = xiLeWangHistoryService.updateByPrimaryKeySelective(xiLeWangHistory);
        if(i == 0){
            json.put("code",0);
            json.put("errMsg","更新失败!");
        }else{
            json.put("code",1);
            json.put("errMsg","更新成功");
            json.put("id",xiLeWangHistory.getId());
        }
        return json.toJSONString();
    }

    @GetMapping
    public PageInfo<XiLeWangHistory> selectByOpenid(@RequestParam("pageNo") Integer pageNo ,@RequestParam("pageSize") Integer pageSize){
        return this.xiLeWangHistoryService.selectByOpenid(Current.getOpenid(), pageNo, pageSize);
    }

    @DeleteMapping("/{id}")
    public JSONObject deleteHistory(@PathVariable("id") Long id){
        XiLeWangHistory xiLeWangHistory = new XiLeWangHistory();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setDeleted(true);
        int i = this.xiLeWangHistoryService.updateByPrimaryKeySelective(xiLeWangHistory);
        if(i > 0){
            json.put("code",1);
            json.put("errMsg","删除成功");
        }else{
            json.put("code",0);
            json.put("errMsg","删除失败");
        }
        return json;
    }
}
