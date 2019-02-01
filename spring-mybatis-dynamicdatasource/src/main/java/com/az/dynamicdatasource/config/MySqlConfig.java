package com.az.dynamicdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.az.dynamicdatasource.datasource.DynamicDataSourceContextHolder;
import com.az.dynamicdatasource.datasource.DynamicRoutingDataSource;
import com.az.dynamicdatasource.eum.DataSourceKeys;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = {MySqlConfig.PACKAGE},
        sqlSessionFactoryRef = "SqlSessionFactory" )
public class MySqlConfig {

     static final String PACKAGE="com.az.dynamicdatasource.mapper";
     static final String MAPPING_LOCATION="classpath:com/az/dynamicdatasource/mapping/*.xml";
     static final String TYPE_PACKAGE="com.az.dynamicdatasource.entry";


    @ConfigurationProperties(prefix = "spring.datasource.master.druid.mysql")
    @Bean("masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }


    @ConfigurationProperties(prefix = "spring.datasource.salve-a.druid.mysql")
    @Bean("salveADataSource")
    public DataSource salveADataSource(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean("dynamicDatasource")
    public DataSource dynamicDatasource(){
        DynamicRoutingDataSource dynamicRoutingDataSource=new DynamicRoutingDataSource();
        Map<Object,Object> dataSourceMap= new HashMap<>();
        dataSourceMap.put(DataSourceKeys.master.getName(),masterDataSource());
        dataSourceMap.put(DataSourceKeys.salveA.getName(),salveADataSource());

        dynamicRoutingDataSource.setDefaultTargetDataSource(masterDataSource());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.datasourceKey.addAll(dataSourceMap.keySet());

        DynamicDataSourceContextHolder.salveDatasourceKey.addAll(dataSourceMap.keySet());
        DynamicDataSourceContextHolder.salveDatasourceKey.remove(DataSourceKeys.master.getName());
        return dynamicRoutingDataSource;
    }

    @Bean("SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDatasource")
                         DataSource dynamicDatasource)throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean=
                        new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDatasource);
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_PACKAGE);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{});
        sqlSessionFactoryBean.setMapperLocations(
                        new PathMatchingResourcePatternResolver()
                        .getResources(MAPPING_LOCATION));

        return sqlSessionFactoryBean.getObject();
    }



}
