import com.az.dynamicdatasource.Application;
import com.az.dynamicdatasource.entry.Student;
import com.az.dynamicdatasource.eum.Sex;
import com.az.dynamicdatasource.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class DynamicDatasourceTest {

    private Log logger= LogFactory.getLog(this.getClass());

    @Autowired
    StudentService studentService;

    @Test
    public void queryStudentTest(){
        try {
            Map<String,Object> params=new HashMap<>();
            params.put("id",4);
            List<Student> studentList=studentService.queryStudents(params);
            if(!studentList.isEmpty())
                logger.info("queryStudentTest查询成功："+studentList.get(0).toString());
        } catch (Exception e) {
            logger.error("queryStudentTest查询异常",e);
        }
    }


    @Test
    public void TestInsertStudent() {
        try {
            Student student = new Student();
            student.setName("李四");
            student.setAge(12);
            student.setClassID(1);
            student.setSex(Sex.Male);

            studentService.insertStudent(student);
            logger.info("TestInsertStudent插入成功:" + student.toString());
        } catch (Exception e) {
            logger.error("TestInsertStudent报错:", e);
        }
    }


    @Test
    public void test(){
        logger.info(MessageFormat.format("hello,this is {0} .","azhen"));
    }
}
