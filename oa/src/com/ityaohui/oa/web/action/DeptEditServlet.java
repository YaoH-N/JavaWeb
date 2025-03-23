package com.ityaohui.oa.web.action; /**
 * Author: 小牛
 * Date: 2025/3/16 18:17
 * Description:
 */

import com.ityaohui.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DeptEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取应用的根路径
        String contextPath = request.getContextPath();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + "    <title>修改部门</title>\n" + "</head>\n" + "<body>\n" + "    <h1>修改部门</h1>\n" + "    <hr>\n" + "    <form action=\"" + contextPath + "/dept/modify\" method=\"post\">");
        // 获取部门编号
        String deptno = request.getParameter("deptno");
        // 连接数据库，根据部门编号查询信息
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("        部门编号 <input type=\"text\" name=\"deptno\" value=\"" + deptno + "\" readonly><br>\n" + "        部门名称 <input type=\"text\" name=\"dname\" value=\"" + dname + "\"><br>\n" + "        部门位置 <input type=\"text\" name=\"loc\" value=\"" + loc + "\"><br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("        <input type=\"submit\" value=\"修改\"><br>\n" + "    </form>\n" + "</body>\n" + "</html>");
    }
}
