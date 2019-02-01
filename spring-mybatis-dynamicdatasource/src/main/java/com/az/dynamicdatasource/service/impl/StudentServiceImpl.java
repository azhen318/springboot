package com.az.dynamicdatasource.service.impl;

import com.az.dynamicdatasource.entry.Student;
import com.az.dynamicdatasource.mapper.StudentMapper;
import com.az.dynamicdatasource.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    @Qualifier("studentMapper")
    StudentMapper studentMapper;

    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public List<Student> queryStudents(Map<String, Object> params) {
        return studentMapper.queryStudents(params);
    }
}
