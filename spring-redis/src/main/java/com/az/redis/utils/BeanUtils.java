package com.az.redis.utils;

import com.az.redis.eum.Sex;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
/**
 * @author quzhengguo
 */
public class BeanUtils {

    /**
     * @see 对象转Map
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String,String> obj2Map(Object obj)
            throws IllegalAccessException{
        Map<String,String> map=new HashMap<>();
        Class clazz=obj.getClass();
        Field[] fields= clazz.getDeclaredFields();
        for(Field field:fields){
            String name=field.getName();
            Boolean acc=field.isAccessible();
            field.setAccessible(true);
            Object value=field.get(obj);
            if(value instanceof Date)
                value=DateUtils.time2YYYYMMDDHHmmss((Date)value);
            field.setAccessible(acc);
            map.put(name,value.toString());
        }

        return map;
    }}
