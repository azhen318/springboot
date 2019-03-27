import com.alibaba.fastjson.JSONObject;
import com.az.redis.Application;
import com.az.redis.entry.Student;
import com.az.redis.utils.BeanUtils;
import com.az.redis.utils.DateUtils;
import com.az.redis.utils.RedisUtils;
import com.az.redis.utils.RedissonUtils;
import com.google.common.util.concurrent.RateLimiter;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    private static Log logger= LogFactory.getLog(RedisTest.class);

    private static Integer count= 0;

    @Autowired
    private RedissonClient redissonClient;



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
    /**
     * @see 测试Redis获取Zset元素
     */
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


    @Test
    /**
     * @see 测试用Redis实现分布式锁。
     *  redis是单机部署时可以用该方法实现分布式锁；
     *  如果是多机部署的话可以尝试用Redisson实现分布式锁
     *
     *  现实分布式锁的三种方式：
     *      1）数据库乐观锁
     *      2）redis分布式锁
     *      3）zookkeep分布式锁
     *  @apiNote 关于redis加锁都是先setNX()获取锁，然后再setExpire()设置锁的有效时间。
     *　　　然而这样的话获取锁的操作就不是原子性的了，如果setNX后系统宕机，就会造成锁死，系统阻塞。
     */
    public void testRedisLock(){

        ExecutorService service= Executors.newFixedThreadPool(20);
        for(int i=0;i<20;i++){
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Boolean lock=RedisUtils.addValIfAbsent("az:springboot:redis:test:lock",Thread.currentThread().getName());
                    if(lock){
                        RedisUtils.setExpiredTime("az:springboot:redis:test:lock",300L, TimeUnit.MILLISECONDS);
                        count++;
                        logger.info("获取分布式锁成功："+Thread.currentThread().getName()+";count值："+count);
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e) {
                        }
                    }else{
                        logger.info("获取分布式锁失败："+Thread.currentThread().getName());
                    }

                }
            }
        });}


        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
        }

    }


    @Test
    /**
     * @see Redis分布式架构redisson
     * 参考地址：https://github.com/mrniko/redisson/wiki
     */
    public void testRedisson(){
        RList<String> list=redissonClient.getList("az:springboot:redisson:test:list");
        Boolean bList=list.add("A");

        RMap<String,String> map=
                redissonClient.getMap("az:springboot:redisson:test:map");
        String pre=map.put("A","A1");

        pre=map.put("A","A2");

        RSet<String> set=
                redissonClient.getSet("az:springboot:redisson:test:set");
        set.add("A");
        set.add("A1");
        set.add("A3");
        Boolean bSet=set.add("A");
    }


    @Test
    /**
     * @see redisson实现分布式锁
     */
    public void testRedissonLock(){

        ExecutorService executorService=Executors.newFixedThreadPool(20);
        final String key="redissonlock";

        for(int i=0;i<20;i++){
            while(true){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        Boolean res=RedissonUtils.tryLock(key,TimeUnit.MILLISECONDS,100,200);
                        if(res){
                            logger.info(MessageFormat.format("{0}成功，count值【{1}】",Thread.currentThread().getName(),count));
                            count++;
                        }else{
                            logger.error(Thread.currentThread().getName()+"失败");
                        }

                    }
                });
            }
        }

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
        }
    }

    private RateLimiter rateLimiter=RateLimiter.create(3.0);

    @Test
    /**
     * @see guava 令牌限流测试;
     *  同功能的api还有semaphore
     *  @apiNote
     *    acquire():从RateLimiter获取一个许可，该方法会被阻塞直到获取到请求
     *    tryAcquire():从RateLimiter 获取许可，如果该许可可以在无延迟下的情况下立即
     *                  获取得到的话
     *    tryAcquire(long timeout,TimeUnit unit):从RateLimiter获取许可如果该许可
     *                  可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout
     *                  过期之前获取得到许可的话，那么立即返回false（无需等待）。该方法等同于
     *                  tryAcquire(1, timeout, unit)。
     */
    public void testGuava(){

        while (true){
            Boolean sleep=rateLimiter.tryAcquire(3);
            if(sleep){
                logger.info("获取时间"+DateUtils.time2YYYYMMDDHHmmss(new Date()));
            }else{
                logger.error("失败:"+sleep);
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }



    }

}
