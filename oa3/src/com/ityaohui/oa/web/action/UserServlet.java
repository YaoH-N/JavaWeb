package com.ityaohui.oa.web.action;

import com.ityaohui.oa.bean.User;
import com.ityaohui.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: 小牛
 * Date: 2025/3/30 23:40
 * Description:
 */
@WebServlet({"/user/login", "/user/logout"})
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/logout".equals(servletPath)) {
            doExit(request, response);
        }
    }

    protected void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 从session域中删除 user 对象
            session.removeAttribute("user");
            // 手动销毁session对象
            session.invalidate();
            // 销毁cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("username".equals(name) || "password".equals(name)) {
                        // 这个cookie要销毁掉
                        cookie.setMaxAge(0);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                }
            }
            // 跳转到登录界面
            response.sendRedirect(request.getContextPath());
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = false;
        // 验证用户名密码是否正确
        // 获取用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 连接数据库验证用户名和密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        // 登录成功/失败
        if (success) {
            // 获取session对象 这里要求必须获取到session对象，没有session也要创建一个session
            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
            User user = new User(username, password);
            session.setAttribute("user", user);

            // 登陆成功，并且用户确实选择了“十天免登录”功能。
            String flag = request.getParameter("flag");
            if ("1".equals(flag)) {
                // 创建cookie对象，存储用户名密码
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                // 设置cookie的有效期为十天
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);
                // 设置cookie的path（只要访问这个应用，浏览器就一定要携带这两个cookie
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                // 相应给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            // 登录成功
            response.sendRedirect("/oa/dept/list");
        } else {
            // 登录失败
            response.sendRedirect("/oa/error.jsp");
        }
    }
}
