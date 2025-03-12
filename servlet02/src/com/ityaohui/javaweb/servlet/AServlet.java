package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/7 10:49
 * Description:
 */
public class AServlet implements Servlet {
    public AServlet(){
        System.out.println("AServlet 无参数构造方法执行了");
    }

    // init 方法只执行一次
    // 在AServlet对象第一次被创建后执行
    // init 方法通常是完成初始化操作的
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AServlet's init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // service 方法：是处理用户请求的核心方法
    // 只要用户发送一次请求，service 方法必然会执行一次
    // 发送100次请求，service方法则执行100次
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("AServlet's service method execute!");
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    // destroy 方法也是只执行一次
    // tomcat服务器在销毁AServlet对象之前会调用destroy方法
    // destroy方法在执行的时候，AServlet对象的内存还没有被销毁，即将被销毁  实例方法的调用对象必须存在
    // destroy方法可以编写销毁前的准备，关闭资源的代码
    @Override
    public void destroy() {
        System.out.println("AServlet's destroy method execute!");
    }
}
