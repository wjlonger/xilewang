package com.wuwei.provider.jd.config;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.wuwei.base.util.AES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdCpsConfig {

    public JdCpsConfig(){}

    @Value("${jdcps.serverurl}")
    private  String serverUrl;

    @Value("${jdcps.appkey}")
    private String appKey ;

    @Value("${jdcps.appsecret}")
    private String appSecret;

    @Value("${jdcps.accesstoken}")
    private String accessToken;

    @Bean("jdClient")
    public JdClient jdClient(){
        return new DefaultJdClient(serverUrl,accessToken,AES.decode(appKey),AES.decode(appSecret));
    }
}
