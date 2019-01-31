package com.az.mybatis.multi.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
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

@Configuration
@MapperScan(basePackages = {MySqlConfigPrimary.PACKAGE},
        sqlSessionFactoryRef = "primarySqlSessionFactory",
        sqlSessionTemplateRef ="sqlSessionTemplatePrimary" )
public class MySqlConfigPrimary {

     static final String PACKAGE="com.az.mybatis.multi.mapper.primary";
     static final String MAPPING_LOCATION="classpath:com/az/mybatis/multi/mapping/primary/*.xml";
     static final String TYPE_PACKAGE="com.az.mybatis.multi.entry";


    @ConfigurationProperties(prefix = "spring.datasource.primary.druid.mysql")
    @Bean
    @Primary
    public DataSource primaryDataSource(){
        DruidDataSourceBuilder builder=
                       new DruidDataSourceBuilder();
        return builder.build();
    }

    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource")
                         DataSource dataSource)throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean=
                        new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_PACKAGE);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{});
        sqlSessionFactoryBean.setMapperLocations(
                        new PathMatchingResourcePatternResolver()
                        .getResources(MAPPING_LOCATION));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplatePrimary")
    public SqlSessionTemplate sqlSessionTemplatePrimary(@Qualifier("primarySqlSessionFactory")
                                SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }

}
