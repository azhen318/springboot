package com.az.mybatis.multi.entry;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class Class {

    private Integer id;

    private Integer grade;

    private Integer clas;

    private String master;

    private Integer count;

    private Date create_time;


    static final String dateFormat="YYYY-MM-DD HH:mm:ss";

    public static String date2YYYYMMDDHHmmss(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat(Class.dateFormat);
        return sdf.format(date);
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", grade=" + grade +
                ", clas=" + clas +
                ", master='" + master + '\'' +
                ", count=" + count +
                ", create_time=" + Class.date2YYYYMMDDHHmmss(create_time) +
                '}';
    }
}
