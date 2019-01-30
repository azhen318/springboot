package com.az.springinterceptor.interceptor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("loginIntercetor")
public class LoginInterceptor implements HandlerInterceptor{

    private Log logger= LogFactory.getLog(this.getClass());



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {


        Boolean fLogin=Boolean.FALSE;
        String userName=null;
        String uri=request.getRequestURI();

        try {
            fLogin=Boolean.valueOf(
                    request.getSession().getAttribute("fLogin").toString());
        } catch (Exception e) {
        }

        if(fLogin) {
            userName = request.getSession().getAttribute("userName").toString();
            logger.info("用户："+userName+"登录了网址："+uri);
        }else{
            response.sendRedirect("/springinterceptor/login");
        }

        return fLogin;
    }
}
