package com.wuwei.provider.wechat.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.util.StringUtils;
import com.wuwei.base.wechat.model.XiLeWangJdOrder;
import com.wuwei.base.wechat.model.vo.XiLeWangJdOrderVo;
import com.wuwei.base.wechat.service.XiLeWangJdOrderService;
import com.wuwei.provider.wechat.mapper.XiLeWangJdOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("xiLeWangJdOrderService")
public class XiLeWangJdOrderServiceImpl implements XiLeWangJdOrderService {

    @Autowired
    private XiLeWangJdOrderMapper xiLeWangJdOrderMapper;

    @Override
    public int insert(XiLeWangJdOrder xiLeWangJdOrder) {
        if(null == xiLeWangJdOrder){
            return 0;
        }
        xiLeWangJdOrder.setGmtCreate(new Date());
        xiLeWangJdOrder.setGmtModified(new Date());
        return xiLeWangJdOrderMapper.insert(xiLeWangJdOrder);
    }

    @Override
    public int insertSelective(XiLeWangJdOrder xiLeWangJdOrder) {
        if(null == xiLeWangJdOrder){
            return 0;
        }
        xiLeWangJdOrder.setGmtCreate(new Date());
        xiLeWangJdOrder.setGmtModified(new Date());
        return xiLeWangJdOrderMapper.insertSelective(xiLeWangJdOrder);
    }

    @Override
    public XiLeWangJdOrder selectByPrimaryKey(Long orderId) {
        if(null == orderId || 0 == orderId){
            return null;
        }
        return xiLeWangJdOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangJdOrder xiLeWangJdOrder) {
        if(null == xiLeWangJdOrder){
            return 0;
        }
        xiLeWangJdOrder.setGmtModified(new Date());
        return xiLeWangJdOrderMapper.updateByPrimaryKeySelective(xiLeWangJdOrder);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangJdOrder xiLeWangJdOrder) {
        if(null == xiLeWangJdOrder){
            return 0;
        }
        xiLeWangJdOrder.setGmtModified(new Date());
        return xiLeWangJdOrderMapper.updateByPrimaryKey(xiLeWangJdOrder);
    }

    @Override
    public List<XiLeWangJdOrder> selectByOpenid(String openid) {
        if(StringUtils.isNullOrEmpty(openid)){
            return null;
        }
        return xiLeWangJdOrderMapper.selectByOpenid(openid);
    }

    @Override
    public PageInfo<XiLeWangJdOrderVo> listByOpenidAndViladCode(Integer pageNo, Integer pageSize, String openid, Integer validCode) {
        if(StringUtils.isNullOrEmpty(openid)){
            return null;
        }
        if(null == pageNo || pageNo < 1){
            pageNo = 1;
        }
        if(null == pageSize || pageSize < 1){
            pageSize = 20;
        }
        PageHelper.startPage(pageNo, pageSize);
        List<XiLeWangJdOrderVo> xiLeWangJdOrderVos = xiLeWangJdOrderMapper.listByOpenidAndViladCode(openid, validCode);
        return new PageInfo<>(xiLeWangJdOrderVos);
    }

}
