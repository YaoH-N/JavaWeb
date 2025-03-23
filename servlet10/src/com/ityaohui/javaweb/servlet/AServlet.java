package com.ityaohui.javaweb.servlet;

import com.ityaohui.javaweb.bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/23 00:28
 * Description:
 */
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contextPath = request.getContextPath();

        User user = new User();
        user.setId("111111");
        user.setName("杰克");

        // 将用户对象存储到请求域中去
        request.setAttribute("user", user);

        // 转发  只有一次请求  浏览器地址栏不会改变
        // request.getRequestDispatcher("/b").forward(request, response);

        // 重定向  重定向的路径当中需要以项目名开始，需要添加项目名
        // response 对象将这个路径，"/servlet10/b" 相应给浏览器
        // 重定向发送两次请求，浏览器地址栏会改变
        response.sendRedirect(contextPath + "/b");

    }
}
