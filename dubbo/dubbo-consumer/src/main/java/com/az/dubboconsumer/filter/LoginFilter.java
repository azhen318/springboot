package com.az.dubboconsumer.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author quzhengguo
 * @date 2019/01/25
 * */
@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    private Log logger= LogFactory.getLog(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String scheme= servletRequest.getScheme();
        String service=servletRequest.getServerName();
        Integer port=servletRequest.getServerPort();
        String path=((HttpServletRequest)servletRequest).getRequestURI();
//       String url=servletRequest.getRequestDispatcher()
        logger.info("返问地址为："+scheme+"://"+service+":"+port+path);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        ExecutorService threadPool =new ThreadPoolExecutor(3,5,1000, TimeUnit.MICROSECONDS,new ArrayBlockingQueue<>(10));
    }
}
