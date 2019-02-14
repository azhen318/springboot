package com.az.redis.entry;

import com.az.redis.eum.Sex;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable{


    private Integer id;

    private String name;

    private Integer age;

    private Sex sex;

    private Date createTime;

    public Student(){

    }




    public Student(Integer id, String name, Integer age,Date createTime,int code){
        this.id=id;
        this.name=name;
        this.age=age;
        this.createTime=createTime;
        this.sex=Sex.getSexByCode(code);
    }


}
