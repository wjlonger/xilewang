package com.wuwei.consumer.wechat.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {

    public static byte[] toByteArray(InputStream in){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bytes = new byte[1024];
            int n;
            while ((n = in.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes, 0, n);
            }
        } catch (Exception e) {
        }
        return byteArrayOutputStream.toByteArray();
    }

}
