package com.az.dubboprovider.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.az.dubboprovider"})
@ImportResource(locations = "classpath:/dubbo-config.xml")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
