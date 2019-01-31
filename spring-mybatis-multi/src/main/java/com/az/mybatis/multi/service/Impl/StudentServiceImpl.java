package com.az.mybatis.multi.service.Impl;

import com.az.mybatis.multi.entry.Student;
import com.az.mybatis.multi.mapper.primary.StudentMapper;
import com.az.mybatis.multi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    @Qualifier("studentMapper")
    StudentMapper studentMapper;

    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }
}
