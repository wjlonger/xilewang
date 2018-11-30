package com.wuwei.provider.wechat.test.service;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.base.wechat.service.XiLeWangUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiLeWangUserServiceTest {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @Test
    public void testUnionThemeGoodsServiceQueryExplosiveGoods(){
        XiLeWangUser xiLeWangUser = new XiLeWangUser();
        xiLeWangUser.setGender(30);
        xiLeWangUser.setOpenid("2132132");
        xiLeWangUserService.insertSelective(xiLeWangUser);
    }

}
