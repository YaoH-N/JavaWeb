package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/7 16:56
 * Description:
 */
public class VipServlet extends GenericServlet{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Vip 可以享受更好的服务！");
    }
}
