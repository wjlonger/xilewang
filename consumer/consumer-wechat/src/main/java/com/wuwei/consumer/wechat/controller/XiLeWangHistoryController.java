package com.wuwei.consumer.wechat.controller;

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

    @Autowired
    private XiLeWangHistoryService xiLeWangHistoryService;

    @PostMapping("/")
    public Long insert(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return null;
        }
        long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        xiLeWangHistory.setCreateTime(System.currentTimeMillis());
        int i = xiLeWangHistoryService.insert(xiLeWangHistory);
        return i == 0 ? null : id;
    }

    @PostMapping("/insertSelective")
    public Long insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return null;
        }
        long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        xiLeWangHistory.setCreateTime(System.currentTimeMillis());
        int i = xiLeWangHistoryService.insertSelective(xiLeWangHistory);
        return i == 0 ? null : id;
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public Long updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory || null == xiLeWangHistory.getId()) {
            return null;
        }
        int i = xiLeWangHistoryService.updateByPrimaryKeySelective(xiLeWangHistory);
        return i == 0 ? null : xiLeWangHistory.getId();
    }

    @PutMapping("/")
    public Long updateByPrimaryKey(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory || null == xiLeWangHistory.getId()){
            return null;
        }
        int i  = xiLeWangHistoryService.updateByPrimaryKey(xiLeWangHistory);
        return i == 0 ? null : xiLeWangHistory.getId();
    }

    @PostMapping("/save")
    public long save(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return 0L;
        }
        if(null == xiLeWangHistory.getId()){
            return this.insertSelective(xiLeWangHistory);
        }
        return this.updateByPrimaryKeySelective(xiLeWangHistory);
    }

}
