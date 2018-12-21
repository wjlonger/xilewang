package com.wuwei.consumer.wechat.controller;


import com.github.pagehelper.PageInfo;
import com.wuwei.base.wechat.model.XiLeWangFavorite;
import com.wuwei.base.wechat.model.vo.XiLeWangFavoriteVo;
import com.wuwei.consumer.wechat.service.XiLeWangFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/favorite")
public class XiLeWangFavoriteController {

    @Autowired
    private XiLeWangFavoriteService xiLeWangFavoriteService;

    @PostMapping
    public int insertSelective(@RequestBody XiLeWangFavorite xiLeWangFavorite){
        return this.xiLeWangFavoriteService.insertSelective(xiLeWangFavorite);
    }

    @GetMapping("/{openid}/{skuId}")
    public XiLeWangFavorite selectByPrimaryKey(@PathVariable("openid") String openid, @PathVariable("skuId") Long skuId){
        return this.xiLeWangFavoriteService.selectByPrimaryKey(openid, skuId);
    }

    @PutMapping
    public int updateByPrimaryKeySelective(@RequestBody XiLeWangFavorite xiLeWangFavorite){
        return this.xiLeWangFavoriteService.updateByPrimaryKeySelective(xiLeWangFavorite);
    }

    @GetMapping("/{openid}")
    public PageInfo<XiLeWangFavoriteVo> selectByOpenid(@PathVariable("openid") String openid, @RequestParam("pageNo") Integer pageNo,
                                                       @RequestParam("pageSize") Integer pageSize){
        return this.xiLeWangFavoriteService.selectByOpenid(openid, pageNo, pageSize);
    }

}
