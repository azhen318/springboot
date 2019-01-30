package com.az.springinterceptor.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class LoginController {

    private Log logger= LogFactory.getLog(this.getClass());

    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        Map<String,String[]> param=request.getParameterMap();
        try {
            if (param.containsKey("userName")){
                String userName=param.get("userName")[0];
                request.getSession().setAttribute("fLogin",Boolean.TRUE);
                request.getSession().setAttribute("userName",userName);

                response.sendRedirect("/springinterceptor/hello");

            }else{
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().print("please input userName!好");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }


    }

}
