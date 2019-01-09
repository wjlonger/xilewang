package com.wuwei.provider.wechat.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<XiLeWangIncomeReport> listXiLeWangIncomeReport(Integer pageNo, Integer pageSize, String openid, Integer state) {
        PageInfo<XiLeWangIncomeReport> xiLeWangIncomeReportPageInfo = PageHelper.startPage(pageNo,pageSize).setOrderBy("gmt_create desc")
                .doSelectPageInfo(()->this.xiLeWangIncomeReportMapper.listXiLeWangIncomeReport(openid, state));
        return xiLeWangIncomeReportPageInfo;
    }

    @Override
    public List<XiLeWangIncomeReport> selectBySkuInfoId(Long skuInfoId) {
        if(null == skuInfoId || skuInfoId <= 0){
            return null;
        }
        return xiLeWangIncomeReportMapper.selectBySkuInfoId(skuInfoId);
    }

    @Override
    public XiLeWangIncomeReport selectByOpenidAndSkuInfoId(String openid, Long skuInfoId) {
        if(StringUtils.isNullOrEmpty(openid) || null == skuInfoId || skuInfoId <= 0){
            return null;
        }
        return xiLeWangIncomeReportMapper.selectByOpenidAndSkuInfoId(openid,skuInfoId);
    }

    @Override
    public double totalPending(String openid) {
        if(StringUtils.isNullOrEmpty(openid)){
            return new Double(0);
        }
        return xiLeWangIncomeReportMapper.totalPending(openid);
    }

    @Override
    public double invitePending(String openid){
        if(StringUtils.isNullOrEmpty(openid)){
            return 0.0;
        }
        return this.xiLeWangIncomeReportMapper.invitPending(openid);
    }

    @Override
    public double invitTotal(String openid) {
        if(StringUtils.isNullOrEmpty(openid)){
            return 0.0;
        }
        return this.xiLeWangIncomeReportMapper.invitTotal(openid);
    }

}
