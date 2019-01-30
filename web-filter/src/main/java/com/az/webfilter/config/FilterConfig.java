package com.az.webfilter.config;

import com.az.webfilter.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TimeFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("timeFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
