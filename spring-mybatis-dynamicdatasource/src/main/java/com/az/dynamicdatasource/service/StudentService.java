package com.az.dynamicdatasource.service;


import com.az.dynamicdatasource.entry.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    public void insertStudent(Student student);


    public List<Student> queryStudents(Map<String,Object> params);

}
