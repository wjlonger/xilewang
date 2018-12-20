package com.wuwei.base.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.vo.XiLeWangAssistanceVo;

public interface XiLeWangAssistanceService {

    int insertSelective(XiLeWangAssistance xiLeWangAssistance);

    XiLeWangAssistance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance);

    XiLeWangAssistance selectByOpenIdAndSkuId(String openId, Long skuId);

    PageInfo<XiLeWangAssistanceVo> selectByOpenIdAndState(String openid,Integer state, Integer pageNo, Integer pageSize);
}
