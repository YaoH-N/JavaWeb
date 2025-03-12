package com.ityaohui.javaweb.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: 小牛
 * Date: 2025/3/10 10:48
 * Description:
 */
public class GetServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 响应内容到浏览器上
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>from get servlet</title>\n" + "</head>\n" + "<body>\n" + "<h1>from get servlet</h1>\n" + "</body>\n" + "</html>");

    }
}
