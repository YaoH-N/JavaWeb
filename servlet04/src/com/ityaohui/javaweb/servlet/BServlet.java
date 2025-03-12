package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: 小牛
 * Date: 2025/3/7 23:38
 * Description:
 */
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();
        out.print("ServletContext对象是：" + application + "<br>");

        // log
        // 这个日志自动记录到 CATALINA_HOME/logs
//        application.log("hello 这是一个日志！！！");
        int age = 17;
        // 当年龄小于18的时候表示非法，记录日志
        if (age < 18) {
            application.log("未成年禁止入内！", new RuntimeException("小屁孩，快走开，不适合你！"));
        }
        // 取出来
        Object userObj = application.getAttribute("userObj");
        out.print(userObj + "<br>");
    }
}
