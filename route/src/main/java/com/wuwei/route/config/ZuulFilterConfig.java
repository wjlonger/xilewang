package com.wuwei.route.config;

import com.wuwei.route.filter.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfig {

    @Bean
    public RequestFilter accessFilter(){
        return new RequestFilter();
    }

}
