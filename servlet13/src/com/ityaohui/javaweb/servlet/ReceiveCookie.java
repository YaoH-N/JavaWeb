package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/4/1 00:11
 * Description:
 */
@WebServlet("/sendCookie")
public class ReceiveCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies(); // 如果没有cookie，则返回null，而不是返回长度为0的数组
        if(cookies != null){
            for (Cookie cookie: cookies){
                // 获取cookie的name和value
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name+" "+value);
            }
        }
    }
}
