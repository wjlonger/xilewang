package com.wuwei.provider.wechat.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.util.IdGenerator;
import com.wuwei.base.wechat.model.XiLeWangAssistance;
import com.wuwei.base.wechat.model.vo.XiLeWangAssistanceVo;
import com.wuwei.base.wechat.service.XiLeWangAssistanceService;
import com.wuwei.provider.wechat.mapper.XiLeWangAssistanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("xiLeWangAssistanceService")
public class XiLeWangAssistanceServiceImpl implements XiLeWangAssistanceService {

    @Autowired
    private XiLeWangAssistanceMapper xiLeWangAssistanceMapper;

    @Override
    public int insertSelective(XiLeWangAssistance xiLeWangAssistance) {
        if(null == xiLeWangAssistance){
            return 0;
        }
        if(null == xiLeWangAssistance.getId()){
            xiLeWangAssistance.setId(IdGenerator.nextId());
        }
        xiLeWangAssistance.setGmtCreate(new Date());
        xiLeWangAssistance.setGmtModified(new Date());
        return xiLeWangAssistanceMapper.insertSelective(xiLeWangAssistance);
    }

    @Override
    public XiLeWangAssistance selectByPrimaryKey(Long id) {
        if(null == id || 0 == id){
            return null;
        }
        return xiLeWangAssistanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangAssistance xiLeWangAssistance) {
        if(null == xiLeWangAssistance || null ==  xiLeWangAssistance.getId()){
            return 0;
        }
        xiLeWangAssistance.setGmtModified(new Date());
        return xiLeWangAssistanceMapper.updateByPrimaryKeySelective(xiLeWangAssistance);
    }

    @Override
    public XiLeWangAssistance selectByOpenIdAndSkuId(String openId, Long skuId) {
        if(StringUtils.isEmpty(openId) || null == skuId || 0 == skuId){
            return null;
        }
        return xiLeWangAssistanceMapper.selectByOpenIdAndSkuId(openId,skuId);
    }

    @Override
    public PageInfo<XiLeWangAssistanceVo> selectByOpenIdAndState(String openid, Integer state, Integer pageNo, Integer pageSize) {
        PageInfo<XiLeWangAssistanceVo> xiLeWangAssistanceVoPageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy("gmt_create desc")
                .doSelectPageInfo(()-> this.xiLeWangAssistanceMapper.selectByOpenIdAndState(openid,state));
        return xiLeWangAssistanceVoPageInfo;
    }


}
