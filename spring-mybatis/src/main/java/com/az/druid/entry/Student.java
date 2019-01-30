package com.az.druid.entry;

import com.az.druid.eum.Sex;
import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable{


    private Integer id;

    private String name;

    private Integer age;

    private Sex sex;


    public Student(Integer id,String name, Integer age, Sex sex){
        this.id=id;
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
    public Student(Integer id,String name, Integer age, Integer sex){
        this.id=id;
        this.name=name;
        this.age=age;
        this.sex=Sex.getSexByCode(sex);
    }

    public Student(String name, Integer age, Sex sex){
        this.id=id;
        this.name=name;
        this.age=age;
        this.sex=sex;
    }

}
