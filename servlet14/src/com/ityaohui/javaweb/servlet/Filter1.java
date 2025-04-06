package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/4/5 15:24
 * Description:
 */
/* 以下这个路径属于模糊匹配中的扩展匹配。以星号*开始，这种路径不要以/开始。 */
//@WebFilter("*.do")
//    前缀匹配。要以/开始
//@WebServlet("/dept/*")
//    匹配所有路径
//@WebServlet("/*")
@WebFilter({"/a.do", "/b.do"})
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter1 init method execute!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter1 doFilter method 执行了！");
        chain.doFilter(request, response);
        System.out.println("Filter1 doFilter method 执行结束！");
    }

    @Override
    public void destroy() {
        System.out.println("Filter1 destroy method execute!");
    }
}
