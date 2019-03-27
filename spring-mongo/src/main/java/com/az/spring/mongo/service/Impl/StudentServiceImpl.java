package com.az.spring.mongo.service.Impl;

import com.az.spring.mongo.dao.StudentDao;
import com.az.spring.mongo.entry.Student;
import com.az.spring.mongo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quzhengguo
 */
@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentDao studentDao;

    @Override
    public void saveStud(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryByName(String name) {
        return studentDao.findByName(name);
    }
}
