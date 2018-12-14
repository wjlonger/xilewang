package com.wuwei.provider.service.test;

import com.wuwei.base.wechat.model.vo.XiLeWangJdOrderVo;
import com.wuwei.provider.wechat.ProviderWeChatApplication;
import com.wuwei.provider.wechat.mapper.XiLeWangJdOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderWeChatApplication.class)
public class XiLeWangJdOrderServiceTest {

    @Autowired
    private XiLeWangJdOrderMapper xiLeWangJdOrderMapper;

    @Test
    public void listByOpenidAndViladCodeTest (){
        List<XiLeWangJdOrderVo> xiLeWangJdOrderVoPageInfo = xiLeWangJdOrderMapper.listByOpenidAndViladCode("oVFUM5D0TQvF9sG6yZlVYm0rYaUg",null);
        System.out.println(xiLeWangJdOrderVoPageInfo);
    }

}
