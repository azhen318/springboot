package com.az.dubboconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
*@author quzhengguo
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.az.dubboconsumer"})
@ImportResource("classpath:/dubbo-config.xml")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
