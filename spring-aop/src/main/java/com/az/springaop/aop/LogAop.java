package com.az.springaop.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class LogAop {

    private Log logger= LogFactory.getLog(this.getClass());

    @Pointcut("execution(* com.az.springaop.controller..*.*(..))")
    public void logAopName(){
    }

    @Before("logAopName()")
    public void logBefore(JoinPoint joinPoint){

        logger.info("@before 执行的类为："+joinPoint.getSignature().getDeclaringTypeName());
        logger.info("@before 执行的方法为："+joinPoint.getSignature().getName());

        logger.info("@before 执行的参数为："+joinPoint.getArgs().toString());
        logger.info("@before 执行的目标为："+joinPoint.getTarget());
    }

    @AfterReturning(pointcut="logAopName()",returning = "retVal")
    public void logAfter(Object retVal){
        logger.info("@AfterReturning 返回结果为："+retVal);


    }
}
