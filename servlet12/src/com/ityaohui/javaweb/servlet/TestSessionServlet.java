package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: 小牛
 * Date: 2025/3/31 15:50
 * Description:
 */
@WebServlet("/test/session")
public class TestSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request 和 session都是服务器端的Java对象，都在jvm中
        // request 对象代表了一次请求。（一次请求对应一个request对象，两次请求就会对应两个不同的request对象）
        // session对象代表了一次会话。（一次会话对应一个session对象）
        // 获取session对象
        HttpSession session = request.getSession();
        // 将session对象相应到浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("会话对象:" + session);


    }
}
