package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: 小牛
 * Date: 2025/3/23 16:27
 * Description:
 * @WebServlet(urlPatterns = {"/welcome"})
 * 当注解的属性是一个数组，并且数组中只有一个元素，大括号可以省略
 * @WebServlet(value = "/welcome")
 * 这个value属性和urlPatterns属性一致，都是用来指定Servlet的映射路径的
 * 如果注解的属性名试value的话，属性名也是可以省略的
 */
@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("欢迎学习servlet！");

    }
}
