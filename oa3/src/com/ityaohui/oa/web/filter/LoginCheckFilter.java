package com.ityaohui.oa.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/4/5 16:13
 * Description:
 */
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 获取session，这个session是不需要新建的，只是获取当前session，获取不到放回null
        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("username") != null) {
//            chain.doFilter(request, response);
//        } else {
//            // 跳转到登录界面
//            response.sendRedirect(request.getContextPath() + "/index.jsp");
//        }
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            // 跳转到登录界面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
