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
 * Date: 2025/4/1 23:37
 * Description:
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
            if (username != null && password != null) {
                // 验证用户名密码是否正确，正确表示登陆成功，错误表示登录失败
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                boolean success = false;
                try {
                    conn = DBUtil.getConnection();
                    String sql = "select * from t_user where username = ? and password = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        // 登录成功
                        success = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DBUtil.close(conn, ps, rs);
                }
                if(success){
                    // 获取session对象 这里要求必须获取到session对象，没有session也要创建一个session
                    HttpSession session = request.getSession();
                    // session.setAttribute("username", username);
                    User user = new User(username, password);
                    session.setAttribute("user", user);
                    // 正确，表示登录成功，并且有cookie，设置了十天免登录
                    response.sendRedirect(request.getContextPath() + "/dept/list");
                }else {
                    // 错误，表示登录失败，cookie中存储的账号密码和数据库中不一致
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
            } else {
                // 跳转到登陆页面
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }
    }
}
