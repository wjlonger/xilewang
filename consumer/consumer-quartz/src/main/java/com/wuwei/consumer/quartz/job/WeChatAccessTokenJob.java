package com.wuwei.consumer.quartz.job;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class WeChatAccessTokenJob {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    @Scheduled(initialDelay=1000,fixedRate=7000000)
    public void xiLeWangWeChatAccessToken(){
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String httpUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", AES.decode(appId), AES.decode(secret));
        try {
            // 创建远程url连接对象
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                String response = sbf.toString();
                JSONObject jsonObject = JSONObject.parseObject(response);
                String errcode = jsonObject.getString("errcode");
                if(null == errcode || "0".equals(errcode)){
                    String accessToken = jsonObject.getString("access_token");
                    if(!StringUtils.isEmpty(accessToken)){
                        stringRedisTemplate.opsForValue().set("access_token",accessToken);
                    }else{
                        this.xiLeWangWeChatAccessToken();
                    }
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
    }

}
