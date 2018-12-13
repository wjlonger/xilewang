package com.wuwei.provider.wechat.serviceimpl;

import com.wuwei.base.util.StringUtils;
import com.wuwei.base.wechat.model.XiLeWangIncomeReport;
import com.wuwei.base.wechat.service.XiLeWangIncomeReportService;
import com.wuwei.provider.wechat.mapper.XiLeWangIncomeReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public XiLeWangIncomeReport selectByProperty(Integer type, String openid, Long jdOrderId, Integer jdOrderSkuIndex) {
        if(null == type || StringUtils.isNullOrEmpty(openid) || null == jdOrderId || null == jdOrderSkuIndex){
            return null;
        }
        return xiLeWangIncomeReportMapper.selectByProperty(type, openid, jdOrderId, jdOrderSkuIndex);
    }

    @Override
    public List<XiLeWangIncomeReport> selectByJdOrderId(Long jdOrderId) {
        if(null == jdOrderId){
            return null;
        }
        return xiLeWangIncomeReportMapper.selectByJdOrderId(jdOrderId);
    }

}
