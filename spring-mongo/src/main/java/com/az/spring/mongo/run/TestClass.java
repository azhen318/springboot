package com.az.spring.mongo.run;

import com.az.spring.mongo.entry.Student;
import com.az.spring.mongo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author quzhengguo
 */
@Component
public class TestClass implements ApplicationRunner {

    @Autowired
    StudentService studentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       /* Student stud=new Student();
        stud.setName("azhen");
        stud.setAge(19);
        studentService.saveStud(stud);
        int i=0;*/

        List<Student> list =studentService.queryByName("azhen");
        int i=0;

    }
}
