package com.az.dubbointerface.inter;

import com.az.dubbointerface.entry.Student;

public interface ApiProviderStudent {

    public Student queryByName(String name);

}
