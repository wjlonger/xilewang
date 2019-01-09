package com.wuwei.consumer.wechat.controller;

import com.wuwei.base.util.StringUtils;
import com.wuwei.consumer.wechat.utils.StreamUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
@RequestMapping("/api/wechat/xilewang/img/proxy")
public class XiLeWangImgProxyController {

    @GetMapping
    @ResponseBody
    public void forward(@RequestParam("url") String url, HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new URL(url).openStream();
            byte[] bytes = StreamUtils.toByteArray(inputStream);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
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

    private String getFileName(String url){
        if(StringUtils.isNullOrEmpty(url)){
            return StringUtils.EMPTY;
        }
        int index = url.lastIndexOf("/");
        if(index == -1){
            return url;
        }
        return url.substring(index + 1);
    }

}
