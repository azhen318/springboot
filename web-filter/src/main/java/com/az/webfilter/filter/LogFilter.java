package com.az.webfilter.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "logFilter",urlPatterns = {"/*"})
public class LogFilter implements Filter {

    private Log logger= LogFactory.getLog(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       String scheme= servletRequest.getScheme();
       String service=servletRequest.getServerName();
       String path=((HttpServletRequest)servletRequest).getRequestURI();
//       String url=servletRequest.getRequestDispatcher()
       logger.info("返问地址为："+scheme+"://"+service+":8090"+"/"+path);
       filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
