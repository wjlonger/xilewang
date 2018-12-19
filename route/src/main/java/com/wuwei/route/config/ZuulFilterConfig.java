package com.wuwei.route.config;

import com.wuwei.route.filter.IdentityFilter;
import com.wuwei.route.filter.SignFilter;
import com.wuwei.route.filter.TimestampFilter;
import com.wuwei.route.filter.UniqueCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfig {

    @Bean
    public TimestampFilter timestampFilter(){
        return new TimestampFilter();
    }

    @Bean
    public UniqueCodeFilter uniqueCodeFilter(){
        return new UniqueCodeFilter();
    }

    @Bean
    public SignFilter signFilter(){
        return new SignFilter();
    }

    @Bean
    public IdentityFilter identityFilter(){
        return new IdentityFilter();
    }
}
