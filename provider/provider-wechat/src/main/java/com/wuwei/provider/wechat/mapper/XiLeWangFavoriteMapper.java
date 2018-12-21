package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XiLeWangFavoriteMapper {

    int insertSelective(XiLeWangFavorite xiLeWangFavorite);

    int updateByPrimaryKeySelective(XiLeWangFavorite xiLeWangFavorite);

    XiLeWangFavorite selectByPrimaryKey(@Param("openid") String openid, @Param("skuId") Long skuId);

    List<XiLeWangFavoriteVo> selectByOpenid(@Param("openid") String openid);

}