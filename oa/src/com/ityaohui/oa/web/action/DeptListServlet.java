package com.ityaohui.oa.web.action;

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

/**
 * Author: 小牛
 * Date: 2025/3/15 17:19
 * Description:
 */
public class DeptListServlet extends HttpServlet {

    // 处理post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取应用的根路径，防止项目名被写死
        String contextPath = request.getContextPath();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>部门列表页面</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>部门列表</h1>\n" +
                "    <hr>\n" +
                "    <table border=\"1px\" align=\"center\" width=\"50%\">\n" +
                "        <tr>\n" +
                "            <th>序号</th>\n" +
                "            <th>部门编号</th>\n" +
                "            <th>部门名称</th>\n" +
                "            <th>操作</th>\n" +
                "        </tr>\n");

        // 连接数据库，查询所有的部门
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = DBUtil.getConnection();
            String sql = "select deptno as a,dname,loc from dept";
            // 获取预编译的数据库操作对象
            ps = conn.prepareStatement(sql);
            // 执行sql语句
            rs = ps.executeQuery();
            // 处理结果集
            int i = 0;
            while (rs.next()) {
                String deptno = rs.getString("a");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("<tr>\n" +
                        "            <td>" + (++i) + "</td>\n" +
                        "            <td>" + deptno + "</td>\n" +
                        "            <td>" + dname + "</td>\n" +
                        "            <td>\n" +
                        "                <a href=\"javascript:void(0)\" onclick=\"del(" + deptno + ")\">删除</a>\n" +
                        "                <a href=\"" + contextPath + "/dept/edit?deptno=" + deptno + "\">修改</a>\n" +
                        "                <a href=\"" + contextPath + "/dept/detail?deptno=" + deptno + "\">详情</a>\n" +
                        "            </td>\n" +
                        "        </tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接对象
            DBUtil.close(conn, ps, rs);
        }

        out.print("    </table>\n" +
                "\n" +
                "    <hr>\n" +
                "    <a href=\"/oa/add.html\">新增部门</a>\n" +
                "</body>\n" +
                "<script>\n" +
                "    function del(deptno) {\n" +
                "        if (window.confirm(\"亲，删了不可恢复噢！\")) {" +
                "document.location.href = \"" + contextPath + "/dept/delete?deptno=\"+deptno" +
                "        }\n" +
                "    }\n" +
                "</script>" +
                "</html>");

    }
}
