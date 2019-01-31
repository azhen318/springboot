package com.az.mybatis.multi.service;

import com.az.mybatis.multi.entry.Class;

import java.util.List;
import java.util.Map;

public interface ClassService {

    public void insertClass(Class clas);

    public List<Class> queryClass(Map<String,Object> params);

}
