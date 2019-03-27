package com.az.spring.mongo.service;

import com.az.spring.mongo.entry.Student;

import java.util.List;

/**
 * @author quzhengguo
 */
public interface StudentService {


    public void saveStud(Student student);

    public List<Student> queryByName(String name);

}
