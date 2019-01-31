import com.az.mybatis.multi.Application;
import com.az.mybatis.multi.entry.Class;
import com.az.mybatis.multi.service.ClassService;
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
            logger.info("testInsertClass插入成功:"+clas.toString());
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
            logger.info("testQueryClass查询成功:"+classList.get(0).toString());
        } catch (Exception e) {
            logger.error("testQueryClass报错：",e);
        }

    }
}
