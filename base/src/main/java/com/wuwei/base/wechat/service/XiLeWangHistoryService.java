package com.wuwei.base.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangHistory;

public interface XiLeWangHistoryService {

    XiLeWangHistory selectByPrimaryKey(Long id);

    int insert(XiLeWangHistory xiLeWangHistory);

    int insertSelective(XiLeWangHistory xiLeWangHistory);

    int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory);

    int updateByPrimaryKey(XiLeWangHistory xiLeWangHistory);

    PageInfo<XiLeWangHistory> selectByOpenid(String openid, Integer pageNo, Integer pageSize);

}
