package com.wuwei.consumer.wechat.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
public class FastJsonConfig extends com.alibaba.fastjson.support.config.FastJsonConfig {

    public FastJsonConfig(){
        super();
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class,ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE,ToStringSerializer.instance);
        this.setSerializeConfig(serializeConfig);
    }

}
