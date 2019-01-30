package com.az.druid.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = MysqlConfig.PACKAGE,sqlSessionFactoryRef = "sqlSessionFactory")
public class MysqlConfig  {

    static final String MAPPER_LOCATION="classpath:com/az/druid/mapping/**.xml";

    static final String PACKAGE="com.az.druid.inter";

    static final String transactionExecution="execution (* com.az.druid.service.*.*(..))";


    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    public DataSource dataSource(){
        DruidDataSourceBuilder builder=new DruidDataSourceBuilder();
        return builder.build();
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource")
                         DataSource dataSource)throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean=
                        new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{});
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources(MysqlConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }


  /*
     事务AOP配置两种实现：
      1.实现TransactionManagementConfigurer接口
    @Autowired
    private PlatformTransactionManager txtManager;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txtManager;
    }*/

    @Bean
    public DataSourceTransactionManager txtManager(@Qualifier("dataSource")
                                                               DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager=
                        new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


    /**
     * @see 事务
     * @param txtManager
     * @return
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(@Qualifier("txtManager")
                                                                     DataSourceTransactionManager txtManager) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("select*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(txtManager, attributes);
        advisor.setAdvice(txAdvice);
        return advisor;
    }


}
