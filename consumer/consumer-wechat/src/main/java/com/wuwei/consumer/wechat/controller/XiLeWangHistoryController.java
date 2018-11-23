package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.core.ReferenceByIdMarshaller;
import com.wuwei.base.utils.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangHistory;
import com.wuwei.consumer.wechat.service.XiLeWangHistoryService;
import com.wuwei.consumer.wechat.utils.Current;
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

    @PostMapping("/")
    public String insert(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return null;
        }
        final long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        xiLeWangHistory.setCreateTime(System.currentTimeMillis());
        int i = xiLeWangHistoryService.insert(xiLeWangHistory);
        if(i == 0){
            json.put("code",0);
            json.put("errMsg","保存失败!");
        }else{
            json.put("code",1);
            json.put("errMsg","保存成功");
            json.put("id",id);
        }
        return json.toJSONString();
    }

    @PostMapping("/insertSelective")
    public String insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return null;
        }
        final long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        xiLeWangHistory.setCreateTime(System.currentTimeMillis());
        int i = xiLeWangHistoryService.insertSelective(xiLeWangHistory);
        if(i == 0){
            json.put("code",0);
            json.put("errMsg","保存失败!");
        }else{
            json.put("code",1);
            json.put("errMsg","保存成功");
            json.put("id",id);
        }
        return json.toJSONString();
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public String updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory || null == xiLeWangHistory.getId()) {
            return null;
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

    @PutMapping("/")
    public String updateByPrimaryKey(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory || null == xiLeWangHistory.getId()){
            return null;
        }
        int i  = xiLeWangHistoryService.updateByPrimaryKey(xiLeWangHistory);
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

    @PostMapping("/save")
    public String save(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return null;
        }
        if(null == xiLeWangHistory.getId()){
            return this.insertSelective(xiLeWangHistory);
        }
        return this.updateByPrimaryKeySelective(xiLeWangHistory);
    }

}
