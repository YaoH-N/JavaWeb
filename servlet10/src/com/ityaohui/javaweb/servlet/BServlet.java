package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: 小牛
 * Date: 2025/3/23 00:29
 * Description:
 */
public class BServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求域中取出存储的数据
        Object user = request.getAttribute("user");

        // 输出到浏览器
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("请求域中的对象=" + user);

    }
}
