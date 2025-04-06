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
 * Date: 2025/3/31 23:36
 * Description:
 */
@WebServlet("/cookie/generate")
public class GenerateCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie对象
        Cookie cookie = new Cookie("productid", "123456789123455");
        Cookie cookie1 = new Cookie("username", "zhangsan");
        // 设置cookie在1小时之后失效
        // 设置cookie的有效时间是0，，表示该cookie被删除，主要应用在，使用这种方式删除浏览器上的同名cookie
        // 设置cookie有效期 < 0，表示改cookie不会被存储。（不会被存储到硬盘文件中，会放在浏览器运行内存中，和不设置有效时间是同一个效果
        // cookie.setMaxAge(60 * 60);
        cookie.setMaxAge(-1);
        // 设置cookie的关联路径，表示所有该路径及其子路径下的请求，都会携带改cookie传递给服务器
        cookie.setPath(request.getContextPath());
        cookie1.setPath(request.getContextPath());

        response.addCookie(cookie);
        response.addCookie(cookie1);

    }
}
