package com.az.druid.service;

import com.az.druid.entry.Student;
import com.az.druid.eum.Sex;
import com.az.druid.inter.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    StudentMapper studentMapper;


    @Override
    /**
     * @param  学生信息查询条件
     * @date  2019-01-28
     */
    public List<Student> queryStudent(Map<String, Object> params) {
        return studentMapper.queryStudent(params);
    }

    @Override
    public int insertBatchStudent(List<Student> studentList) {
        return  studentMapper.insertBatchStudent(studentList);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    /**
     * @see 切记不要加throws Exception  这样就不会回滚
     */
    public void insertStudentTrans(List<Student> studentList) {
        for (Student s:studentList) {
            if(s.getSex().equals(Sex.Male)){
                throw new RuntimeException("测试事务");
            };
            studentMapper.insertStudentTrans(s);
        }
    }

    @Override
    /**
     * @see com.az.druid.config.MysqlConfig.defaultPointcutAdvisor
     * AOP注解事务，配置参考上面类的方法
     */
    public void addStudentTrans(List<Student> studentList) {
        for (Student s:studentList
                ) {
            if(s.getSex().equals(Sex.Male)){
                throw new RuntimeException("测试事务");
            };
            studentMapper.insertStudentTrans(s);
        }
    }
}
