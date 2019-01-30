package com.az.druid.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtils implements ApplicationContextAware {

    private  static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(!applicationContext.equals(null))
            ApplicationUtils.applicationContext=applicationContext;
    }

    public static <T> T getObject(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static Object getObject(String name){
        return applicationContext.getBean(name);
    }

}
