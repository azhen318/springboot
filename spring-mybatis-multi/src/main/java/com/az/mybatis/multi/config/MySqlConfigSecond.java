package com.az.mybatis.multi.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.az.mybatis.multi.inteceptor.MybatisInteceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = {MySqlConfigSecond.PACKAGE},
            sqlSessionFactoryRef = "secondSqlSessionFactory",
            sqlSessionTemplateRef = "sqlSessionTemplateFactory")
public class MySqlConfigSecond {
    static final String PACKAGE="com.az.mybatis.multi.mapper.second";
    static final String MAPPING_LOCATION="classpath:com/az/mybatis/multi/mapping/second/*.xml";
    static final String TYPE_PACKAGE="com.az.mybatis.multi.entry";


    @ConfigurationProperties(prefix = "spring.datasource.second.druid.mysql")
    @Bean
    public DataSource secondDataSource(){
        DruidDataSourceBuilder builder=
                new DruidDataSourceBuilder();
        return builder.build();
    }

    @Bean("secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondDataSource")
                                                       DataSource dataSource)throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean=
                new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_PACKAGE);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new MybatisInteceptor()});
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources(MAPPING_LOCATION));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplateFactory")
    public SqlSessionTemplate sqlSessionTemplatePrimary(@Qualifier("secondSqlSessionFactory")
                                                                SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}
