package com.az.springaop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("hello")
    public Map<String,Object> getHello(@RequestParam("user") String name){

        Map<String,Object> resMap=new HashMap<>();
        resMap.put("applicationName","spring-aop");
        resMap.put("welcome",name);
        return resMap;
    }

}
