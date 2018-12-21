package com.wuwei.consumer.wechat.hystric;

import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;
import com.wuwei.consumer.wechat.service.XiLeWangFavoriteService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiLeWangFavoriteServiceHystric implements XiLeWangFavoriteService {

    @Override
    public int insertSelective(XiLeWangFavorite xiLeWangFavorite) {
        return 0;
    }

    @Override
    public XiLeWangFavorite selectByPrimaryKey(String openid, Long skuId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangFavorite xiLeWangFavorite) {
        return 0;
    }

    @Override
    public PageInfo<XiLeWangFavoriteVo> selectByOpenid(String openid, Integer pageNo, Integer pageSize) {
        return new PageInfo<>();
    }
}
