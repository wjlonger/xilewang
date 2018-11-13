package com.wuwei.consumer.wechat.hystric;
import com.wuwei.consumer.wechat.service.WeChatService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatServiceHystric implements WeChatService {

    @Override
    public String code2Session(String code) {
        return "";
    }
}
