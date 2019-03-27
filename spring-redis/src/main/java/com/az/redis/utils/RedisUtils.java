package com.az.redis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
/**
 * RedisUtils Class
 *
 * @author quzhengguo
 * @see Redis工具类
 */
public class RedisUtils {

    private static StringRedisTemplate template;

    @Autowired(required = true)
    public void setTemplate(StringRedisTemplate template){
        RedisUtils.template=template;
    }



    /**
     * @see redis操作map元素
     * @param obj
     * @param map
     */
    public static void addMapKeyAndMap(String obj,Map<String,String> map){
        HashOperations hash=template.opsForHash();
        hash.putAll(obj,map);
    }

    public static void addMapKeyAndVal(String obj,String key,String val){
        HashOperations hash=template.opsForHash();
        hash.put(obj,key,val);
    }

    public static Object getMapAllVal(String obj,Class clazz){
        HashOperations hash=template.opsForHash();
        Map<String,String> map = hash.entries(obj);

        if(clazz==null || clazz.isInstance(map)){
            return map;
        }else{
            String json= JSON.toJSONString(map);
            Object object= JSONObject.parseObject(json,clazz);
            return object;
        }
    }


    /**
     * @see redis操作value元素
     * @param obj
     * @param val
     */
    public static void addVal(String obj,String val){
        ValueOperations  operations=template.opsForValue();
        operations.set(obj,val);
    }

    /**
     * @see redis操作value元素，不存在则插入。适合做分布式锁
     * @param obj
     * @param val
     * @return
     */
    public static Boolean addValIfAbsent(String obj,String val){
        ValueOperations  operations=template.opsForValue();
        return operations.setIfAbsent(obj,val);
    }

    public static String getVal(String obj){
        ValueOperations<String,String> operations=template.opsForValue();
        return operations.get(obj);
    }

    /**
     * @see redis操作list集合
     * @param key
     * @param val
     */
    public static void addList(String key,String ... val){
        ListOperations<String,String> operations=template.opsForList();
        operations.rightPushAll(key,val);
    }

    public static void addList(String key,List<String> val){
        ListOperations<String,String> operations=template.opsForList();
        operations.rightPushAll(key,val);
    }

    /**
     * @see 获取list第一个元素,并删除该元素，先进先出原则;
     * @param key
     * @return
     * @apiNote 不想删除可以使用rang（）方法
     */
    public static String getListFirstOne(String key){
        ListOperations<String,String> operations=template.opsForList();
        return operations.leftPop(key);
    }

    /**
     * @see 获取List最后一个元素,并删除该元素，后进先出原则
     * @param key
     * @return
     */
    public static String getListLastOne(String key){
        ListOperations<String,String> operations=template.opsForList();
        return operations.rightPop(key);
    }

    /**
     * @see 获取list批量元素,不会删除元素
     * @param key
     * @param start 0为第一个元素
     * @param end   -1为最后一个元素
     * @return
     */
    public static List<String> getList(String key,Long start,Long end){
        ListOperations<String,String> operations=template.opsForList();
        return operations.range(key,start,end);
    }

    /**
     * @see 添加Redis set元素
     * @param key
     * @param val
     */
    public static void addSet(String key,String ... val){
        SetOperations<String,String> operations=template.opsForSet();
        operations.add(key,val);
    }

    /**
     * @see 获取redis set元素，并删除元素
     * @param key
     * @param count 返回元素个数
     * @apiNote 不删除元素可以使用set.members（key）
     * @return
     */
    public static List<String> getSet(String key,Long count){
        SetOperations<String,String> operations=template.opsForSet();
        return operations.pop(key,count);
    }


    /**
     * @see 获取redis set元素所有元素 ，不删除
     * @param key
     * @return
     */
    public static Set<String> getSetVal(String key){
        SetOperations<String,String>  set=template.opsForSet();
        return set.members(key);
    }

    /**
     * @see 添加redis zset元素
     * @param key
     * @param val
     * @param score 权重排序的先后 从小到大
     */
    public static void addZset(String key,String val,double score){
        ZSetOperations<String,String> operations=template.opsForZSet();
        operations.add(key,val,score);
    }


    /**
     * @see  获取redis的zset元素,不会删除元素
     * @param key
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @return
     */
    public static Set<String> getZsetByIndex(String key,Long start,Long end){
        ZSetOperations<String,String> operations=template.opsForZSet();
        return operations.range(key,start,end);
    }


    /**
     * @see 获取redis的zset元素,不会删除元素
     * @param key
     * @param min 最小socre
     * @param max 最大socre
     * @apiNote 包含头和尾
     * @return
     */
    public static Set<String> getZsetByScore(String key,double min,double max){
        ZSetOperations<String,String> operations=template.opsForZSet();
        return operations.rangeByScore(key,min,max);
    }


    /**
     * @see 删除指定元素
     * @param key
     * @param val
     * @return 删除元素个数
     */
    public static Long delZsetByVal(String key,String ... val){
        ZSetOperations<String,String> operations=template.opsForZSet();
        return operations.remove(key,val);
    }

    /**
     * @see 根据范围删除元素
     * @param key
     * @param start 开始位置
     * @param end   结束位置
     * @apiNote 删除包含头和尾
     * @return 删除元素个数
     */
    public static Long delZsetByRange(String key,Long start,Long end){
        ZSetOperations<String,String> operations=template.opsForZSet();
        return operations.removeRange(key,start,end);
    }

    /**
     * @see 根据score删除元素
     * @param key
     * @param min 最小score
     * @param max   最大score
     * @apiNote 删除包含头和尾
     * @return
     */
    public static Long delZsetByScore(String key,double min,double max){
        ZSetOperations<String,String> operations=template.opsForZSet();
        return operations.removeRangeByScore(key,min,max);
    }






    /**
     * @see 设置key的有效时间
     * @param key
     * @param time
     * @param unit
     */
    public static void setExpiredTime(String key, Long time, TimeUnit unit){
        template.expire(key,time,unit);
    }


}
