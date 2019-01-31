package com.az.mybatis.multi.mapper.second;

import com.az.mybatis.multi.entry.Class;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassMapper {

    public void insertClass(Class clas);

    public List<Class> queryClass(Map<String, Object> params);
}
