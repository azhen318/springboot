package com.az.springinterceptor.config;

import com.az.springinterceptor.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private LoginInterceptor loginIntercetor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercetor)
                .addPathPatterns("/*")
                .excludePathPatterns("/login");
    }

    @Bean
    public HttpMessageConverter<String> httpMessageConverter(){
        StringHttpMessageConverter stringHttpMessageConverter=
                    new StringHttpMessageConverter(Charset.forName("utf-8"));
        return stringHttpMessageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(httpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
