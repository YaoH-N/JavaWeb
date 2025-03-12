package com.ityaohui.javaweb.servlet;

import com.sun.jdi.Value;
import jakarta.servlet.*;
import jdk.javadoc.doclet.Reporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Author: 小牛
 * Date: 2025/3/7 20:52
 * Description:
 */
public class ConfigTestServlet2 extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // 获取ServletConfig对象
        ServletConfig servletConfig = this.getServletConfig();
        out.print("ServletConfig对象是：" + servletConfig);

        String value = servletConfig.getInitParameter("key");
        out.print("<br>" + value);

    }
}
