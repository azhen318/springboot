package com.az.mybatis.multi.service.Impl;

import com.az.mybatis.multi.entry.Class;
import com.az.mybatis.multi.mapper.second.ClassMapper;
import com.az.mybatis.multi.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
/**
 * @author quzhengguo
 */
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public void insertClass(Class clas) {
        classMapper.insertClass(clas);
    }

    @Override
    public List<Class> queryClass(Map<String, Object> params) {
        return classMapper.queryClass(params);
    }
}
