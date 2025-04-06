package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Author: 小牛
 * Date: 2025/3/23 16:05
 * Description:
 */
@WebServlet(name = "hello", urlPatterns = {"/hello1", "/hello2", "/hello3"},
//        loadOnStartup = 1,
        initParams = {@WebInitParam(name = "username", value = "root"), @WebInitParam(name = "password", value = "123456")})
public class HelloServlet extends HttpServlet {

    public HelloServlet() {
        System.out.println("无参数构造方法执行，HelloServlet加载完成");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 获取servlet name
        String servletName = getServletName();
        out.print("servlet name = " + servletName + "<br>");

        // 获取servlet path
        String servletPath = request.getServletPath();
        out.print("servlet path = " + servletPath + "<br>");

        // 获取初始化参数
        Enumeration<String> names = getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = getInitParameter(name);
            out.print(name + " = " + value + "<br>");
        }

    }
}
