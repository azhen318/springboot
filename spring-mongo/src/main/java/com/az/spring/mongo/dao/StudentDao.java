package com.az.spring.mongo.dao;

import com.az.spring.mongo.entry.Student;

import java.util.List;

/**
 * @author quzhengguo
 */
public interface StudentDao {

    public void insertStudent(Student student);

    public List<Student> findByName(String name);

}
