package com.az.webfilter.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {

    private Log logger= LogFactory.getLog(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long sTime=System.currentTimeMillis();

        filterChain.doFilter(servletRequest,servletResponse);

        Long eTime=System.currentTimeMillis();

        logger.info("过滤器耗时："+(eTime-sTime)+"毫秒");

    }

    @Override
    public void destroy() {

    }
}
