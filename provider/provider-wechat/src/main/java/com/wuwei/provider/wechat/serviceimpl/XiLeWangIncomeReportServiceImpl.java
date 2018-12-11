package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.util.StringUtils;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.base.wechat.service.XiLeWangIncomeReportService;
import com.wuwei.provider.wechat.mapper.XiLeWangIncomeReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("xiLeWangIncomeReportService")
public class XiLeWangIncomeReportServiceImpl implements XiLeWangIncomeReportService {

    @Autowired
    private XiLeWangIncomeReportMapper xiLeWangIncomeReportMapper;

    @Override
    public int insert(XiLeWangIncomeReport xiLeWangIncomeReport) {
        if(null == xiLeWangIncomeReport){
            return 0;
        }
        xiLeWangIncomeReport.setGmtCreate(new Date());
        xiLeWangIncomeReport.setGmtModified(new Date());
        return xiLeWangIncomeReportMapper.insert(xiLeWangIncomeReport);
    }

    @Override
    public int insertSelective(XiLeWangIncomeReport xiLeWangIncomeReport) {
        if(null == xiLeWangIncomeReport){
            return 0;
        }
        xiLeWangIncomeReport.setGmtCreate(new Date());
        xiLeWangIncomeReport.setGmtModified(new Date());
        return xiLeWangIncomeReportMapper.insertSelective(xiLeWangIncomeReport);
    }

    @Override
    public XiLeWangIncomeReport selectByPrimaryKey(Long id) {
        if(null == id || 0 == id){
            return null;
        }
        return xiLeWangIncomeReportMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XiLeWangIncomeReport xiLeWangIncomeReport) {
        if(null == xiLeWangIncomeReport){
            return 0;
        }
        xiLeWangIncomeReport.setGmtModified(new Date());
        return xiLeWangIncomeReportMapper.updateByPrimaryKeySelective(xiLeWangIncomeReport);
    }

    @Override
    public int updateByPrimaryKey(XiLeWangIncomeReport xiLeWangIncomeReport) {
        if(null == xiLeWangIncomeReport){
            return 0;
        }
        xiLeWangIncomeReport.setGmtModified(new Date());
        return xiLeWangIncomeReportMapper.updateByPrimaryKey(xiLeWangIncomeReport);
    }

    @Override
    public XiLeWangIncomeReport selectByOpenidAndJdOrderIdAndSkuId(String openid, Long jdOrderId,Long skuId) {
        if(StringUtils.isNullOrEmpty(openid) || null == jdOrderId || null == skuId){
            return null;
        }
        return xiLeWangIncomeReportMapper.selectByOpenidAndJdOrderIdAndSkuId(openid,jdOrderId,skuId);
    }
}
