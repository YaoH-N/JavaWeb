package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Author: 小牛
 * Date: 2025/3/7 23:37
 * Description:
 */

/**
 * ServletContext
 */
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();
        out.print("ServletContext对象是：" + application + "<br>");
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String initParameterName = initParameterNames.nextElement();
            out.print("\t" + initParameterName + " = " + application.getInitParameter(initParameterName) + "<br>");
        }

        // 获取context path （获取上下文的根）
        String contextPath = application.getContextPath();
        out.print("contextPath = " + contextPath + "<br>");

        // 获取文件的绝对路径
        String realPath = application.getRealPath("/index.html");
        out.print("realPath = " + realPath + "<br>");

        // 准备数据
        User user = new User("jack", "123");
        // 向ServletContext应用域中存储数据
        application.setAttribute("userObj", user);
        // 取出来
        Object userObj = application.getAttribute("userObj");
        out.print(userObj + "<br>");
    }
}
