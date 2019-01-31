package com.az.mybatis.multi.mapper.primary;

import com.az.mybatis.multi.entry.Student;
import org.springframework.stereotype.Repository;

@Repository("studentMapper")
public interface StudentMapper {

    public void insertStudent(Student student);

}
