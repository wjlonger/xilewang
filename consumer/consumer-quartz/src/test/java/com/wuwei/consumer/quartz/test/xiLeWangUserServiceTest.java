package com.wuwei.consumer.quartz.test;

import com.wuwei.consumer.quartz.ConsumerQuartzApplication;
import com.wuwei.consumer.quartz.service.XiLeWangUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerQuartzApplication.class)
public class xiLeWangUserServiceTest {

    @Autowired
    private XiLeWangUserService xiLeWangUserService;

    @Test
    public void updateMoneyTest(){
        int i = xiLeWangUserService.updateMoneyByPrimaryKey(0, 1.0,"oVFUM5Gk27ZyODihNoXRd5XHpPJ0");
        System.out.println(i);
    }

}
