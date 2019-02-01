package com.az.dynamicdatasource.aop;

import com.az.dynamicdatasource.datasource.DynamicDataSourceContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Aspect
@Component
public class DatasourceAspect {

    private static final Log logger = LogFactory.getLog(DatasourceAspect.class);

    private final String[] QUERY_PREFIX = {"select","query"};

    @Pointcut("execution(* com.az.dynamicdatasource.mapper.*.*(..))")
    public void datasourcePointCut(){

    }

    @Before("datasourcePointCut()")
    public void preExecut(JoinPoint joinPoint){
        Boolean isQueryMethod = isQueryMethod(joinPoint.getSignature().getName());
        if (isQueryMethod) {
            DynamicDataSourceContextHolder.useSalveDatasource();
            logger.info(MessageFormat.format("Switch DataSource to [{0}] in Method [{1}]",
                    DynamicDataSourceContextHolder.getDatasourceKey(), joinPoint.getSignature()));
        }

    }


    @After("datasourcePointCut())")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDatasource();
        logger.info(MessageFormat.format("Restore DataSource to [{0}] in Method [{1}]",
                DynamicDataSourceContextHolder.getDatasourceKey(), point.getSignature()));
    }

    private Boolean isQueryMethod(String methodName) {
        for (String prefix : QUERY_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

}
