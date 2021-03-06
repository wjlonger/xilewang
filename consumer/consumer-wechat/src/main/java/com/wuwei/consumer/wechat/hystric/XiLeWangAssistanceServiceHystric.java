package com.wuwei.consumer.wechat.hystric;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.vo.XiLeWangAssistanceVo;
import com.wuwei.consumer.wechat.service.XiLeWangAssistanceService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangAssistanceServiceHystric implements XiLeWangAssistanceService {

    @Override
    public int insertSelective(XiLeWangAssistance xiLeWangAssistance) {
        return 0;
    }

    @Override
    public XiLeWangAssistance selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public XiLeWangAssistance selectByOpenIdAndSkuId(String openId, Long skuId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance) {
        return 0;
    }

    @Override
    public PageInfo<XiLeWangAssistanceVo> selectByOpenIdAndState(String openid, Integer state, Integer pageNo, Integer pageSize) {
        return new PageInfo<>();
    }

}
