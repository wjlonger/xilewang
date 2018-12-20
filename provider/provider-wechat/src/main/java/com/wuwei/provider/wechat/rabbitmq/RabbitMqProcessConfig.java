package com.wuwei.provider.wechat.rabbitmq;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.base.wechat.service.XiLeWangUserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class RabbitMqProcessConfig {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @RabbitListener(queues = "xilewang_user_save")
    public void xiLeWangHistoryInsert(XiLeWangUser xiLeWangUser){
        if(null == xiLeWangUser || StringUtils.isEmpty(xiLeWangUser.getOpenid())){
            return;
        }
        if(null == xiLeWangUserService.selectByPrimaryKey(xiLeWangUser.getOpenid())){
            if(!StringUtils.isEmpty(xiLeWangUser.getMasterOpenid())){
                if(xiLeWangUser.getOpenid().equals(xiLeWangUser.getMasterOpenid()) || null == xiLeWangUserService.selectByPrimaryKey(xiLeWangUser.getMasterOpenid())){
                    xiLeWangUser.setMasterOpenid(com.wuwei.base.util.StringUtils.EMPTY);
                }
            }
            xiLeWangUserService.insertSelective(xiLeWangUser);
            return;
        }
        xiLeWangUser.setMasterOpenid(com.wuwei.base.util.StringUtils.EMPTY);
        xiLeWangUserService.updateByPrimaryKeySelective(xiLeWangUser);
    }

}
