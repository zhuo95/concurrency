package com.zz.concurrency;


import com.zz.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //首先强转成httpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        log.info("do filter,{},{}",Thread.currentThread().getId(),request.getServletPath());
        //add
        RequestHolder.add(Thread.currentThread().getId());

        //让请求继续被处理
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
