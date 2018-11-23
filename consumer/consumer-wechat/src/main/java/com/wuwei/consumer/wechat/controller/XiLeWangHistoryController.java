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

    @GetMapping("/{id}")
    public XiLeWangHistory selectByPrimaryKey(@PathVariable("id") Long id){
        return xiLeWangHistoryService.selectByPrimaryKey(id);
    }

    @PostMapping("/")
    public long insert(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return 0L;
        }
        long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        int i = xiLeWangHistoryService.insert(xiLeWangHistory);
        return i == 0 ? 0L : id;
    }

    @PostMapping("/insertSelective")
    public long insertSelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return 0L;
        }
        long id = IdGenerator.nextId();
        xiLeWangHistory.setId(id);
        xiLeWangHistory.setOpenid(Current.getOpenid());
        int i = xiLeWangHistoryService.insertSelective(xiLeWangHistory);
        return i == 0 ? 0 : id;
    }

    @PutMapping("/updateByPrimaryKeySelective")
    public long updateByPrimaryKeySelective(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory) {
            return 0L;
        }
        int i = xiLeWangHistoryService.updateByPrimaryKeySelective(xiLeWangHistory);
        return i == 0 ? 0L : xiLeWangHistory.getId();
    }

    @PutMapping("/")
    public long updateByPrimaryKey(@RequestBody XiLeWangHistory xiLeWangHistory){
        if(null == xiLeWangHistory){
            return 0L;
        }
        int i  = xiLeWangHistoryService.updateByPrimaryKey(xiLeWangHistory);
        return i == 0 ? 0L : xiLeWangHistory.getId();
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
