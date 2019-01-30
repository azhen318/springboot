package com.az.druid;


import com.az.druid.entry.Student;
import com.az.druid.eum.Sex;
import com.az.druid.inter.StudentMapper;
import com.az.druid.service.StudentService;
import com.az.druid.utils.ApplicationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = {"com.az.druid"})
public class Application implements CommandLineRunner{


    private Log logger= LogFactory.getLog(this.getClass());

    @Autowired
    StudentService studentService;

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }


    /**
     * Spring启动时 需要进行的动作
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
