package com.wuwei.consumer.jd.controller;

import com.wuwei.base.jd.model.GoodsSearch;
import com.wuwei.consumer.jd.service.HomeService;
import jd.union.open.goods.query.response.CommissionInfo;
import jd.union.open.goods.query.response.GoodsResp;
import jd.union.open.goods.query.response.UnionOpenGoodsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/jd")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Value("${goods.ratio}")
    private double ratio;

    @Value("${math.scale}")
    private int scale;

    @GetMapping("/slideShow")
    public String slideShow(){
        return homeService.slideShow();
    }

    @PostMapping("/explosiveGoods")
    public UnionOpenGoodsQueryResponse explosiveGoods(@RequestBody GoodsSearch goodsSearch){
        UnionOpenGoodsQueryResponse unionOpenGoodsQueryResponse = homeService.explosiveGoods(goodsSearch);
        if(null != unionOpenGoodsQueryResponse){
            GoodsResp[] goodsResps = unionOpenGoodsQueryResponse.getData();
            if(null != goodsResps && goodsResps.length > 0){
                for(GoodsResp goodsResp : goodsResps){
                    if(null != goodsResp){
                        CommissionInfo[] commissionInfos = goodsResp.getCommissionInfo();
                        if(null != commissionInfos && commissionInfos.length > 0){
                            for(CommissionInfo commissionInfo : commissionInfos){
                                if(null != commissionInfo){
                                    commissionInfo.setCommission(new BigDecimal(commissionInfo.getCommission()).multiply(new BigDecimal(ratio)).divide(new BigDecimal(100)).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue());
                                }
                            }
                        }
                    }
                }
            }
        }
        return unionOpenGoodsQueryResponse;
    }
}
