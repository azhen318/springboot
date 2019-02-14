import com.alibaba.fastjson.JSONObject;
import com.az.redis.Application;
import com.az.redis.entry.Student;
import com.az.redis.utils.BeanUtils;
import com.az.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    private static Log logger= LogFactory.getLog(RedisTest.class);



    @Test
    /**
     * @see 测试设置和获取redis val类型值
     */
    public void testRedisVal(){
        RedisUtils.addVal("az:springboot:redis:test:hello","azhen");
        String val=RedisUtils.getVal("az:springboot:redis:test:hello");
        logger.info("获取val插入数据："+val);
    }

    @Test
    /**
     * 测试对象转MAP方法
     */
    public void testObj2Map()throws IllegalAccessException{
        Student student=new Student(1,"azhen",27,new Date(),1);

        Map<String,String> map= BeanUtils.obj2Map(student);

        logger.info("对象转MAP"+ JSONObject.toJSONString(map));
    }

    @Test
    /**
     * @see Redis插入Map类型值
     */
    public void testRedisMapAdd() throws IllegalAccessException{
        Student student=new Student(1,"azhen",27,new Date(),1);

        Map<String,String> map= BeanUtils.obj2Map(student);

        RedisUtils.addMapKeyAndMap("az:springboot:redis:test:map:student:"+student.getName(),map);

        logger.info("获取map插入数据："+JSONObject.toJSONString(
                RedisUtils.getMapAllVal("az:springboot:redis:test:map:student:azhen",null)));
    }


    @Test
    /**
     * @see 获取Redis插入map数据并转换student对象
     * @apiNote  目前存在问题：枚举类型无法自动装载
     */
    public void testRedisMapGet(){
        Student student=(Student) RedisUtils.getMapAllVal("az:springboot:redis:test:map:student:azhen",
                                            Student.class);
        logger.info("获取map数据转Student："+student.toString());

    }


    @Test
    /**
     * @see 测试redis 插入list值
     */
    public void testRedisListAdd(){
        List<String> val=new ArrayList<>();
        val.add("A1");
        val.add("B1");
        val.add("C1");
        val.add("D1");
        RedisUtils.addList("az:springboot:redis:test:list","A","B","C","D");
        RedisUtils.addList("az:springboot:redis:test:list",val);
        logger.info("Redis插入list数据正常");
    }

    @Test
    /**
     * @see 测试获取redis中list元素
     */
    public void testRedisListGet(){
        String firstVal=RedisUtils.getListFirstOne("az:springboot:redis:test:list");
        logger.info("Redis list第一个值："+firstVal);
        String lastVal=RedisUtils.getListLastOne("az:springboot:redis:test:list");
        logger.info("Redis list最后一个值："+lastVal);
        List<String> all=RedisUtils.getList("az:springboot:redis:test:list",0L,-1L);
        StringBuilder sb=new StringBuilder();
        for (String str:all) {
            sb.append(str+";");
        }
        logger.info("Redis list内容为："+sb.toString());

    }


    @Test
    /**
     * @see 测试Redis 添加set元素
     */
    public void testRedisSetAdd(){
        RedisUtils.addSet("az:springboot:redis:test:set","A","A","A1","A2");
        logger.info("Redis插入Set数据正常");
    }


    @Test
    /**
     * @see 测试获取Redis set元素
     */
    public void testRedisSetGet(){

        List<String> val=RedisUtils.getSet("az:springboot:redis:test:set",1L);
        StringBuilder sb=new StringBuilder();
        for (String str:val) {
            sb.append(str+";");
        }
        logger.info("Redis Set内容为："+sb.toString());

        sb.delete(0,sb.length());
        Set<String> all=RedisUtils.getSetVal("az:springboot:redis:test:set");

        for (String str:all) {
            sb.append(str+";");
        }
        logger.info("Redis Set所有内容为："+sb.toString());

    }

    @Test
    /**
     * @see 测试Redis 插入Zset元素
     */
    public void testRedisZsetAdd(){
        RedisUtils.addZset("az:springboot:redis:test:zset","A",1);
        RedisUtils.addZset("az:springboot:redis:test:zset","B",4);
        RedisUtils.addZset("az:springboot:redis:test:zset","C",3);
        RedisUtils.addZset("az:springboot:redis:test:zset","D",2);
        logger.info("Redis插入 Zset数据正常");
    }


    @Test
    public void testRedisZsetGet(){
        Set<String> val=RedisUtils.getZsetByIndex("az:springboot:redis:test:zset",0L,-1L);
        StringBuilder sb=new StringBuilder();
        for (String str:val) {
            sb.append(str+";");
        }
        logger.info("Redis Zset根据位置索引获取数据为："+sb.toString());

        Set<String> val1=RedisUtils.getZsetByScore("az:springboot:redis:test:zset",2,3);
        sb.delete(0,sb.length());
        for (String str:val1) {
            sb.append(str+";");
        }
        logger.info("Redis Zset根据socre值范围获取数据为："+sb.toString());

        Long l=RedisUtils.delZsetByVal("az:springboot:redis:test:zset", "D");
        logger.info("Redis Zset根据值删除数据："+l.toString());

        Long ll=RedisUtils.delZsetByRange("az:springboot:redis:test:zset", 0L,1L);
        logger.info("Redis Zset根据值删除数据："+ll.toString());

        Long lll=RedisUtils.delZsetByScore("az:springboot:redis:test:zset", 1,4);
        logger.info("Redis Zset根据值删除数据："+lll.toString());
    }

}
