package com.wuwei.provider.wechat.mapper;

import com.wuwei.base.wechat.model.XiLeWangHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XiLeWangHistoryMapper {

    XiLeWangHistory selectByPrimaryKey(Long id);

    int insert(XiLeWangHistory xiLeWangHistory);

    int insertSelective(XiLeWangHistory xiLeWangHistory);

    int updateByPrimaryKeySelective(XiLeWangHistory xiLeWangHistory);

    int updateByPrimaryKey(XiLeWangHistory xiLeWangHistory);

    List<XiLeWangHistory> selectByOpenid(@Param("selectByOpenid") String openid);

}