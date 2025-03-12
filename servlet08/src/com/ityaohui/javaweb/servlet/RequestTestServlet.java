package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Author: 小牛
 * Date: 2025/3/12 10:20
 * Description:
 */
public class RequestTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(req);

        // 获取客户端的IP地址
        String remoteAddr = req.getRemoteAddr();
        System.out.println("客户端的IP地址：" + remoteAddr); // 客户端的IP地址：127.0.0.1

        // 获取请求方式
        String method = req.getMethod();
        System.out.println(method); // GET

        // 获取请求的uri  带项目名
        String requestURI = req.getRequestURI();
        System.out.println(requestURI); // /servlet08/testRequest

        // 动态获取应用的根路径
        String contextPath = req.getContextPath();
        System.out.println(contextPath); // /servlet08

        // 获取servlet path 不带项目名
        String servletPath = req.getServletPath();
        System.out.println(servletPath); // /testRequest

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);
    }
}
