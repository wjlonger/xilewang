package com.wuwei.base.wechat.service;

import com.wuwei.base.wechat.model.XiLeWangUser;

public interface XiLeWangUserService {

    XiLeWangUser selectById(Long id);

    XiLeWangUser insert(XiLeWangUser xiLeWangUser);

    XiLeWangUser updateById(XiLeWangUser xiLeWangUser);

    XiLeWangUser save(XiLeWangUser xiLeWangUser);

}
