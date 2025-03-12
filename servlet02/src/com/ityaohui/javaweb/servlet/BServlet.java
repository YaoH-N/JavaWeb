package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/7 10:51
 * Description:
 */
public class BServlet implements Servlet {
    public BServlet(){
        System.out.println("BServlet 无参数构造方法执行了");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("BServlet's init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("BServlet's service method execute!");
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("BServlet's destroy method execute!");
    }
}
