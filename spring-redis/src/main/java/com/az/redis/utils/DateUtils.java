package com.az.redis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {

    private static final String format="YYYY-MM-DD HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<>();

    public static String time2YYYYMMDDHHmmss(Date time){
        SimpleDateFormat sdf=threadLocal.get();
        if(sdf==null){
            sdf=new SimpleDateFormat(format);
            threadLocal.set(sdf);
        }
        return sdf.format(time);
    }

}
