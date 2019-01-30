package com.az.dubboconsumer.controller;


import com.az.dubbointerface.entry.Student;
import com.az.dubbointerface.inter.ApiProviderStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

     @Autowired
     @Qualifier("apiProviderStudent")
     ApiProviderStudent apiProviderStudent;


     @RequestMapping("queryByName")
     public Student getStudentByName(@RequestParam("name")
                                     String name){
         return apiProviderStudent.queryByName(name);
     }

     @RequestMapping("hello")
     public String hello(){
          return "hello";
     }

}
