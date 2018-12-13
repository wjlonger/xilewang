package com.wuwei.consumer.quartz.hystric;

import com.wuwei.base.wechat.model.XiLeWangUser;
import com.wuwei.consumer.quartz.service.XiLeWangUserService;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class XiLeWangUserServiceHystric implements XiLeWangUserService {

    @Override
    public XiLeWangUser selectByPrimaryKey(String openid) {
        return null;
    }

    @Override
    public int updateMoneyByPrimaryKey(int type, Double modifyMoney, String openid) {
        System.out.println("进入熔断");
        return 0;
    }

}
