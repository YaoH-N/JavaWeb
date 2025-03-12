package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/7 13:28
 * Description:
 */

/**
 * 编写一个标准通用的Servlet ，起名：GenericServlet
 * 以后所有的Servlet类都不要直接实现Servlet接口了
 * 以后所有的Servlet类都要继承GenericServlet类
 * GenericServlet 就是一个适配器
 */
public abstract class GenericServlet implements Servlet {
    // 成员变量
    private ServletConfig config;

    @Override
    public final void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        this.init();
    }

    public void init(){
        System.out.println("GenericServlet's init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    /**
     * 抽象方法，这个方法最常用，所以要求子类必须实现service方法
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
