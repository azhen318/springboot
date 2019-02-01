import com.az.mybatis.multi.Application;
import com.az.mybatis.multi.entry.Class;
import com.az.mybatis.multi.entry.Student;
import com.az.mybatis.multi.eum.Sex;
import com.az.mybatis.multi.service.ClassService;
import com.az.mybatis.multi.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MybatisTest {

    private Log logger= LogFactory.getLog(this.getClass());

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;


    @Test
    /**
     * @see 插入一条记录
     */
    public void testInsertClass(){

        try {
            Class clas=new Class();
            clas.setGrade(3);
            clas.setClas(3);
            clas.setMaster("王五");
            clas.setCount(60);
            classService.insertClass(clas);
            logger.info("testInsertClass插入成功");
        } catch (Exception e) {
            logger.error("testInsertClass报错：",e);
        }


    }

    @Test
    public void testQueryClass(){
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("id",1);
            List<Class> classList=classService.queryClass(map);
            if(!classList.isEmpty())
                logger.info("testQueryClass查询成功:"+classList.get(0).toString());
        } catch (Exception e) {
            logger.error("testQueryClass报错：",e);
        }

    }


    @Test
    public void TestInsertStudent(){
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("id",1);
            List<Class> classList=classService.queryClass(map);
            Student student=new Student();
            if(!classList.isEmpty()){
                Class clazz=classList.get(0);
                student.setName("张三");
                student.setAge(12);
                student.setClassID(clazz.getId());
                student.setSex(Sex.Male);

                studentService.insertStudent(student);
            }

            logger.info("TestInsertStudent插入成功:"+student.toString());
        } catch (Exception e) {
            logger.error("TestInsertStudent报错:",e);
        }


    }

}
