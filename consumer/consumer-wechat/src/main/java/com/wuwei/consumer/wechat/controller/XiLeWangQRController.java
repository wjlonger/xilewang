package com.wuwei.consumer.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwei.base.util.StringUtils;
import com.wuwei.consumer.wechat.service.XiLeWangWeChatAccessTokenService;
import com.wuwei.consumer.wechat.utils.Current;
import com.wuwei.consumer.wechat.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/api/wechat/xilewang/qr")
public class XiLeWangQRController {

    @Autowired
    private XiLeWangWeChatAccessTokenService xiLeWangWeChatAccessTokenService;

    @GetMapping
    public void getQRCode(@RequestParam(name = "scene", required = false) String scene,
                          @RequestParam(name = "page", required = false) String page,
                          @RequestParam(name = "width", required = false) Integer width,
                          @RequestParam(name = "isHyaline", required = false) Boolean isHyaline,
                          HttpServletResponse response){
        String accessToken = xiLeWangWeChatAccessTokenService.getAccessToken();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            if(!StringUtils.isNullOrEmpty(scene)){
                paramJson.put("scene", scene);
            }else{
                paramJson.put("scene", Current.getOpenid());
            }
            if(!StringUtils.isNullOrEmpty(page)){
                paramJson.put("page", page);
            }
            if(null != width){
                paramJson.put("width", width.intValue());
            }
            if(null != isHyaline){
                paramJson.put("is_hyaline", isHyaline.booleanValue());
            }
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            inputStream = httpURLConnection.getInputStream();
            byte[] bytes = StreamUtils.toByteArray(inputStream);
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        }
        catch (Exception e) {
        }finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }

    }

}
