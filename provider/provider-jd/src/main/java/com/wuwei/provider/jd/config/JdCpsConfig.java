package com.wuwei.provider.jd.config;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
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
        return new DefaultJdClient(serverUrl,accessToken,appKey,appSecret);
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
