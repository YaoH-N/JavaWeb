package com.ityaohui.oa.web.action; /**
 * Author: 小牛
 * Date: 2025/3/16 11:45
 * Description: 根据部门编号查询部门信息，并显示
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

public class DeptDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>部门详情</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>部门详情</h1>\n" +
                "    <hr>");
        // 获取部门编号
        String deptno = request.getParameter("deptno");

        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            // 这个结果集肯定只有一条，应为deptno是主键，不能重复
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("部门编号: " + deptno + " <br>\n" +
                        "  部门名称: " + dname + " <br>\n" +
                        "  部门位置: " + loc + " <br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("<input type=\"button\" value=\"后退\" onclick=\"window.history.back()\">\n" +
                "</body>\n" +
                "</html>");
    }
}
