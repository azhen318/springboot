package com.az.druid.service;

import com.az.druid.entry.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * @param  学生信息查询条件
     * @date 2019-01-28
     */
    public List<Student> queryStudent(Map<String,Object> params);


    public int insertBatchStudent(List<Student> studentList);

    /**
     * @see 主要用于测试注解事务用例
     * @param studentList
     *
     */
    public void insertStudentTrans(List<Student> studentList);

    /**
     * @see 用于AOP配置事务
     * @param studentList
     */
    public void addStudentTrans(List<Student> studentList);
}
