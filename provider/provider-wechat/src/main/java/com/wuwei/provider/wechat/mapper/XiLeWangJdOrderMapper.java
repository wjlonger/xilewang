package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.base.wechat.model.vo.XiLeWangJdOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XiLeWangJdOrderMapper {

    int insert(XiLeWangJdOrder xiLeWangJdOrder);

    int insertSelective(XiLeWangJdOrder xiLeWangJdOrder);

    XiLeWangJdOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(XiLeWangJdOrder xiLeWangJdOrder);

    int updateByPrimaryKey(XiLeWangJdOrder xiLeWangJdOrder);

    List<XiLeWangJdOrder> selectByOpenid(String openid);

    List<XiLeWangJdOrderVo> listByOpenidAndViladCode(@Param("openid") String openid, @Param("validCode") Integer validCode);


}