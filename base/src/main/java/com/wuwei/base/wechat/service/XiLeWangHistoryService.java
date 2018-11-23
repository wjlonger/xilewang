package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangHistory;

public interface XiLeWangHistoryService {

    XiLeWangHistory selectByPrimaryKey(Long id);

    int insert(XiLeWangHistory xiLeWangHistory);

    int insertSelective(XiLeWangHistory xiLeWangHistory);

    int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory);

    int updateByPrimaryKey(XiLeWangHistory xiLeWangHistory);

}
