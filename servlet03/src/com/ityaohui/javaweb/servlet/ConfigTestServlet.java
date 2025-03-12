package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Author: 小牛
 * Date: 2025/3/7 20:33
 * Description:
 */

/**
 * ServletConfig
 * 1. ServletConfig 是什么？
 */
public class ConfigTestServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // 获取ServletConfig对象
        ServletConfig servletConfig = this.getServletConfig();
        // 输出该对象
        out.print("ServletConfig对象是：" + servletConfig.toString());
        out.print("<br>");

        String servletName = servletConfig.getServletName();
        out.print("<servlet-name>" + servletName + "</servlet-name>");
        Enumeration<String> names = servletConfig.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            out.print("\t" + name + "=" + servletConfig.getInitParameter(name) + "<br>");
        }

        // 怎么获取ServletContext对象呢？
        // 第一种方式：通过ServletConfig对象获取ServletContext对象
        ServletContext application = servletConfig.getServletContext();
        // 输出
        out.print("<br>"+application);

        // 第二种方式：通过this也可以获取ServletContext对象
        ServletContext application2 = this.getServletContext();
        out.print("<br>"+application2);
    }
}
