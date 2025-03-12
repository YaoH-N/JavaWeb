package com.ityaohui.javaweb.servlet;

import jakarta.servlet.*;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Author: 小牛
 * Date: 2025/3/6 22:38
 * Description:
 */
public class StudentServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        // 设置相应的内容类型
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        // 连接数据库 JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/bjpowernode?serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            // 获取预编译的数据库操作对象
            String sql = "select no,name from t_student";
            ps = conn.prepareStatement(sql);

            // 执行sql语句
            rs = ps.executeQuery();
            // 处理结果集
            while (rs.next()) {
                String no = rs.getString("no");
                String name = rs.getString("name");
                out.print(no + "\t" + name + "\t <br>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
