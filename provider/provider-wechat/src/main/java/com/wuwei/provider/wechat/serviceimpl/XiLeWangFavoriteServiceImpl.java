package com.wuwei.provider.wechat.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.util.StringUtils;
import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;
import com.wuwei.base.wechat.service.XiLeWangFavoriteService;
import com.wuwei.provider.wechat.mapper.XiLeWangFavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("xiLeWangFavoriteService")
public class XiLeWangFavoriteServiceImpl implements XiLeWangFavoriteService {

    @Autowired
    private XiLeWangFavoriteMapper xiLeWangFavoriteMapper;

    @Override
    public int insertSelective(XiLeWangFavorite xiLeWangFavorite) {
        if(null == xiLeWangFavorite){
            return 0;
        }
        xiLeWangFavorite.setGmtCreate(new Date());
        xiLeWangFavorite.setGmtModified(new Date());
        return this.xiLeWangFavoriteMapper.insertSelective(xiLeWangFavorite);
    }

    @Override
    public XiLeWangFavorite selectByPrimaryKey(String openid, Long skuId) {
        if(StringUtils.isNullOrEmpty(openid) || null == skuId){
            return null;
        }
        return this.xiLeWangFavoriteMapper.selectByPrimaryKey(openid, skuId);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangFavorite xiLeWangFavorite) {
        if(null == xiLeWangFavorite){
            return 0;
        }
        xiLeWangFavorite.setGmtModified(new Date());
        return this.xiLeWangFavoriteMapper.updateByPrimaryKeySelective(xiLeWangFavorite);
    }

    @Override
    public PageInfo<XiLeWangFavoriteVo> selectByOpenid(String openid, Integer pageNo, Integer pageSize) {
        PageInfo<XiLeWangFavoriteVo> xiLeWangFavoriteVoPageInfo = PageHelper.startPage(pageNo,pageSize).setOrderBy("gmt_modified desc")
                .doSelectPageInfo(()->this.xiLeWangFavoriteMapper.selectByOpenid(openid));
        return xiLeWangFavoriteVoPageInfo;
    }
}
