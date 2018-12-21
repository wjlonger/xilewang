package com.wuwei.consumer.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;
import com.wuwei.consumer.wechat.service.XiLeWangFavoriteService;
import com.wuwei.consumer.wechat.service.XiLeWangGoodsService;
import com.wuwei.consumer.wechat.utils.Current;
import jd.union.open.goods.query.response.GoodsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/favorite")
public class XiLeWangFavoriteController {

    @Autowired
    private XiLeWangFavoriteService xiLeWangFavoriteService;

    @Autowired
    private XiLeWangGoodsService xiLeWangGoodsService;

    JSONObject jsonObject = new JSONObject();

    @PostMapping("/{skuId}")
    public JSONObject save(@PathVariable("skuId") Long skuId){
        XiLeWangFavorite xiLeWangFavorite = this.xiLeWangFavoriteService.selectByPrimaryKey(Current.getOpenid(), skuId);
        if(null == xiLeWangFavorite){
            xiLeWangFavorite = new XiLeWangFavorite();
            xiLeWangFavorite.setOpenid(Current.getOpenid());
            xiLeWangFavorite.setSkuId(skuId);
            xiLeWangFavorite.setDeleted(false);
            if(this.xiLeWangFavoriteService.insertSelective(xiLeWangFavorite) > 0){
                jsonObject.put("code",1);
                jsonObject.put("errMsg","关注成功");
            }else{
                jsonObject.put("code",0);
                jsonObject.put("errMsg","关注失败");
            }
        }else{
            xiLeWangFavorite.setDeleted(!xiLeWangFavorite.getDeleted());
            if(this.xiLeWangFavoriteService.updateByPrimaryKeySelective(xiLeWangFavorite) > 0){
                jsonObject.put("code",1);
                if(xiLeWangFavorite.getDeleted()){
                    jsonObject.put("errMsg","取消关注成功");
                }else{
                    jsonObject.put("errMsg","关注成功");
                }
            }else{
                jsonObject.put("code",0);
                if(xiLeWangFavorite.getDeleted()){
                    jsonObject.put("errMsg","取消关注失败");
                }else{
                    jsonObject.put("errMsg","关注失败");
                }
            }
        }
        return jsonObject;
    }

    @GetMapping("/{skuId}")
    public int goodsIsFavorite(@PathVariable("skuId") Long skuId){
        XiLeWangFavorite xiLeWangFavorite = this.xiLeWangFavoriteService.selectByPrimaryKey(Current.getOpenid(), skuId);
        if(null == xiLeWangFavorite){
            return 0;
        }
        return xiLeWangFavorite.getDeleted() ? 0 : 1;
    }

    @GetMapping
    public PageInfo<XiLeWangFavoriteVo> selectByOpenid(@RequestParam("pageNo") Integer pageNo,
                                                       @RequestParam("pageSize") Integer pageSize){
        PageInfo<XiLeWangFavoriteVo> xiLeWangFavoriteVoPageInfo = this.xiLeWangFavoriteService.selectByOpenid(Current.getOpenid(), pageNo, pageSize);
        if(null != xiLeWangFavoriteVoPageInfo){
            List<XiLeWangFavoriteVo> xiLeWangFavoriteVos = xiLeWangFavoriteVoPageInfo.getList();
            if(null != xiLeWangFavoriteVos && xiLeWangFavoriteVos.size() > 0){
                for(XiLeWangFavoriteVo xiLeWangFavoriteVo : xiLeWangFavoriteVos){
                    GoodsResp goodsResp = xiLeWangGoodsService.goodsDetail(xiLeWangFavoriteVo.getSkuId());
                    xiLeWangFavoriteVo.setGoodsResp(goodsResp);
                }
            }
        }
        return xiLeWangFavoriteVoPageInfo;
    }

}
