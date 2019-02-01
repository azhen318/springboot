package com.az.mybatis.multi.inteceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;


/**
 * @author quzhengguo
 * @see Signature type类型只要有，args参考以下具体类，
 * 说明文档：https://www.cnblogs.com/blueSkyline/p/10178992.html
 * 1、Executor MyBatis 对外提供的一个操作接口类
 * 2、ParameterHandler 处理参数
 * 3、ResultSetHandler 结果集
 * 4、StatementHandler 预编译状态的接口
 */
@Intercepts(@Signature(type = Executor.class,method = "query" ,
        args={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class MybatisInteceptor implements Interceptor {

    private Log logger= LogFactory.getLog(this.getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        String sql=getSqlByInvocation(invocation);

        logger.info("拦截SQL:"+sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private String getSqlByInvocation(Invocation invocation){

        final Object[] args=invocation.getArgs();
        MappedStatement mappedStatement=(MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        return boundSql.getSql();


    }

}
