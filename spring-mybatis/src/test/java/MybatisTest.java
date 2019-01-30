import com.alibaba.fastjson.JSON;
import com.az.druid.Application;
import com.az.druid.entry.Student;
import com.az.druid.eum.Sex;
import com.az.druid.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class MybatisTest {

    private Log logger= LogFactory.getLog(this.getClass());

    @Autowired
    StudentService studentService;

    /*插入批量*/
    @Test
    public void testInsert(){
        try {
            List<Student> studentList=new ArrayList<>();
            Student azhen=new Student("屈正国",27, Sex.Male);
            Student wenny=new Student("wenny",28,Sex.Female);
            studentList.add(azhen);
            studentList.add(wenny);
            studentService.insertBatchStudent(studentList);
            logger.info("批量插入成功");
        } catch (Exception e) {
            logger.error("testInsert报错:",e);
        }
    }

    /*查询*/
    @Test
    public void testQuery(){

        try {
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("id",1);
            List<Student> students=studentService.queryStudent(hashMap);
            logger.info("查询结果："+ JSON.toJSONString(students));
        } catch (Exception e) {
            logger.error("testQuery报错:",e);
        }

    }

    @Test
    /**
     * @see 注解事务测试
     * 在MysqlConfig类新增EnableTransactionManagement注解，开启事务
     */
    public void testTrans(){
        try {
            List<Student> studentList=new ArrayList<>();
            Student azhen=new Student("屈正国",27, Sex.Male);
            Student wenny=new Student("wenny",28,Sex.Female);
            studentList.add(wenny);
            studentList.add(azhen);
            studentService.insertStudentTrans(studentList);
            logger.info("批量插入成功");
        } catch (Exception e) {
            logger.error("testInsert报错:",e);
        }
    }


    @Test
    /**
     * @see AOP注解事务测试
     */
    public void testTransAOP(){
        try {
            List<Student> studentList=new ArrayList<>();
            Student azhen=new Student("屈正国",27, Sex.Male);
            Student wenny=new Student("wenny",28,Sex.Female);
            studentList.add(wenny);
            studentList.add(azhen);
            studentService.addStudentTrans(studentList);
            logger.info("批量插入成功");
        } catch (Exception e) {
            logger.error("testInsert报错:",e);
        }
    }

}
