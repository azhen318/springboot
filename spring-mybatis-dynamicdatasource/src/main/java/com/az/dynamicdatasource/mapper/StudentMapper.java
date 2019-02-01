package com.az.dynamicdatasource.mapper;

import com.az.dynamicdatasource.entry.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("studentMapper")
public interface StudentMapper {

    public void insertStudent(Student student);


    public List<Student> queryStudents(Map<String, Object> params);

}
