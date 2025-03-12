package com.ityaohui.javaweb.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/7 16:53
 * Description:
 */
public class UserServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("UserServlet's service method execute!");
    }
}
