package com.az.mybatis.multi.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtils implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(!applicationContext.equals(null))
            ApplicationUtils.applicationContext=applicationContext;
    }

    private ApplicationContext getApplicationContext(){
        return ApplicationUtils.applicationContext;
    }

    public <T> T  getObject(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }


    public Object getObject(String clazzName){
        return getApplicationContext().getBean(clazzName);
    }

    public <T> T getObject(String clazzName,Class<T> clazz){
        return getApplicationContext().getBean(clazzName,clazz);
    }

}
