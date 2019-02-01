package com.az.dynamicdatasource.datasource;

import com.az.dynamicdatasource.eum.DataSourceKeys;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quzhengguo
 */
@Component
public class DynamicDataSourceContextHolder {

    private static final  Log logger= LogFactory.getLog(DynamicDataSourceContextHolder.class);

    /**
     * 用于在切换数据源时保证不会被其他线程修改
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 用于轮循的计数器
     */
    private static int counter = 0;

    public static List<Object> datasourceKey=new ArrayList<>();


    public static List<Object> salveDatasourceKey=new ArrayList<>();

    public static final ThreadLocal<Object> ContextHolder
            =ThreadLocal.withInitial(()->DataSourceKeys.master.getName());

    public static void useSalveDatasource(){
        lock.lock();

        try {
            int datasourceKeyIndex = counter % salveDatasourceKey.size();
            ContextHolder.set(String.valueOf(salveDatasourceKey.get(datasourceKeyIndex)));
            counter++;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Switch slave datasource failed, error message is {0}", e.getMessage()));
            useMasterDataSource();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void useMasterDataSource(){
        ContextHolder.set(DataSourceKeys.master.getName());
    }

    public static void clearDatasource(){
        ContextHolder.remove();
    }

    public static Object getDatasourceKey(){
        return ContextHolder.get();
    }

    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        ContextHolder.set(key);
    }





}
