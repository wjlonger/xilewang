package com.wuwei.consumer.wechat.hystric;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.vo.XiLeWangJdOrderVo;
import com.wuwei.consumer.wechat.service.XiLeWangJdOrderService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangJdOrderServiceHystric implements XiLeWangJdOrderService {

    @Override
    public PageInfo<XiLeWangJdOrderVo> listByOpenidAndViladCode(Integer pageNo, Integer pageSize, String openid, Integer validCode) {
        return new PageInfo<>();
    }
}
