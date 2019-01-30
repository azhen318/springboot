package com.az.druid.inter;

import com.az.druid.entry.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentMapper {

    /**
     * @param  学生信息查询条件
     * @date 2019-01-28
     */
    public List<Student> queryStudent(Map<String,Object> params);


    /**
     * @date 2019-01-30
     * @param studentList
     * @return 插入条数
     */
    public int insertBatchStudent(List<Student> studentList);


    /**
     * @see 单条插入
     * @param student对象
     * @return
     */
    public void  insertStudentTrans(Student student);
}
