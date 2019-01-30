package com.az.dubbointerface.entry;

import com.az.dubbointerface.eum.Sex;
import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable{



    private String name;

    private Integer age;

    private Sex sex;


    public Student(String name,Integer age,Sex sex){
        this.name=name;
        this.age=age;
        this.sex=sex;
    }

}
