package com.az.spring.mongo.dao.Impl;

import com.az.spring.mongo.dao.StudentDao;
import com.az.spring.mongo.entry.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author quzhengguo
 */
@Repository
public class StudentDaoImpl  implements StudentDao{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertStudent(Student student) {
        mongoTemplate.insert(student);
    }

    @Override
    public List<Student> findByName(String name) {

        Criteria criteria=Criteria.where("name").is(name);

        Query query=new Query(criteria);

        return mongoTemplate.find(query,Student.class);
    }
}
