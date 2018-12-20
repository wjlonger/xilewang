package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.vo.XiLeWangAssistanceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XiLeWangAssistanceMapper {

    int insert(XiLeWangAssistance xiLeWangAssistance);

    int insertSelective(XiLeWangAssistance xiLeWangAssistance);

    XiLeWangAssistance selectByPrimaryKey(Long id);

    XiLeWangAssistance selectByOpenIdAndSkuId(@Param("openid") String openId, @Param("skuId") Long skuId);

    int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance);

    int updateByPrimaryKey(XiLeWangAssistance xiLeWangAssistance);

    List<XiLeWangAssistanceVo> selectByOpenIdAndState(@Param("openid") String openid,@Param("state") Integer state);

}