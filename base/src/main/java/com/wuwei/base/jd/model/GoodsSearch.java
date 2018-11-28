package com.wuwei.base.jd.model;

import java.io.Serializable;
import java.util.List;

public class GoodsSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Integer pageIndex;
    private Integer pageSize;
    private List<Long> skuIds;
    private String keyword;
    private Double pricefrom;
    private Double priceto;
    private Integer commissionShareStart;
    private Integer commissionShareEnd;
    private String owner;
    private String sortName;
    private String sort;
    private Integer isCoupon;
    private Integer isPG;
    private Double pingouPriceStart;
    private Double pingouPriceEnd;
    private Integer isHot;
    private String brandCode;
    private Integer shopId;

    public Long getCid1() {
        return cid1;
    }

    public void setCid1(Long cid1) {
        this.cid1 = cid1;
    }

    public Long getCid2() {
        return cid2;
    }

    public void setCid2(Long cid2) {
        this.cid2 = cid2;
    }

    public Long getCid3() {
        return cid3;
    }

    public void setCid3(Long cid3) {
        this.cid3 = cid3;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Long> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<Long> skuIds) {
        this.skuIds = skuIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Double getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(Double pricefrom) {
        this.pricefrom = pricefrom;
    }

    public Double getPriceto() {
        return priceto;
    }

    public void setPriceto(Double priceto) {
        this.priceto = priceto;
    }

    public Integer getCommissionShareStart() {
        return commissionShareStart;
    }

    public void setCommissionShareStart(Integer commissionShareStart) {
        this.commissionShareStart = commissionShareStart;
    }

    public Integer getCommissionShareEnd() {
        return commissionShareEnd;
    }

    public void setCommissionShareEnd(Integer commissionShareEnd) {
        this.commissionShareEnd = commissionShareEnd;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getIsCoupon() {
        return isCoupon;
    }

    public void setIsCoupon(Integer isCoupon) {
        this.isCoupon = isCoupon;
    }

    public Integer getIsPG() {
        return isPG;
    }

    public void setIsPG(Integer isPG) {
        this.isPG = isPG;
    }

    public Double getPingouPriceStart() {
        return pingouPriceStart;
    }

    public void setPingouPriceStart(Double pingouPriceStart) {
        this.pingouPriceStart = pingouPriceStart;
    }

    public Double getPingouPriceEnd() {
        return pingouPriceEnd;
    }

    public void setPingouPriceEnd(Double pingouPriceEnd) {
        this.pingouPriceEnd = pingouPriceEnd;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
