package com.az.dubboprovider.provider;

import com.az.dubbointerface.entry.Student;
import com.az.dubbointerface.eum.Sex;
import com.az.dubbointerface.inter.ApiProviderStudent;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ApiProviderStudentImp implements ApiProviderStudent {

    @Override
    public Student queryByName(String name) {
        Random random=new Random();
        return new Student(name,random.nextInt(20)+1, Sex.Male);
    }
}
