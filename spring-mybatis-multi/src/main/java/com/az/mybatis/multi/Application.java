package com.az.mybatis.multi;

import com.az.mybatis.multi.service.ClassService;
import com.az.mybatis.multi.service.Impl.ClassServiceImpl;
import com.az.mybatis.multi.utils.ApplicationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.az.mybatis.multi"})
public class Application implements CommandLineRunner {

    private Log logger= LogFactory.getLog(this.getClass());

    @Autowired
    ApplicationUtils applicationUtils;

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            ClassService classService=
                        applicationUtils.getObject("classServiceImpl",ClassServiceImpl.class);
            logger.info("clazz信息"+classService.toString());
        } catch (Exception e) {
            logger.error("报错：",e);
        }
    }
}
