package com.wuwei.base.wechat.service;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;

public interface XiLeWangFavoriteService {

    int insertSelective(XiLeWangFavorite xiLeWangFavorite);

    XiLeWangFavorite selectByPrimaryKey(String openid, Long skuId);

    int updateByPrimaryKeySelective(XiLeWangFavorite xiLeWangFavorite);

    PageInfo<XiLeWangFavoriteVo> selectByOpenid(String openid, Integer pageNo, Integer pageSize);

}
