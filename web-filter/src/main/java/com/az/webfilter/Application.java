package com.az.webfilter;

import com.az.webfilter.controller.HelloController;
import com.az.webfilter.utils.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.az.webfilter"})
public class Application implements CommandLineRunner{

    private Log log= LogFactory.getLog(this.getClass());

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("helloController:"+SpringUtils.getBean( HelloController.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }



}
