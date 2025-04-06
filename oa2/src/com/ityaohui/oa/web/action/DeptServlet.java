package com.ityaohui.oa.web.action;

import com.ityaohui.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * Date: 2025/3/24 15:13
 * Description:
 */
//@WebServlet(value = "/dept/*") 模糊匹配
@WebServlet(value = {"/dept/list", "/dept/save", "/dept/edit", "/dept/modify", "/dept/detail", "/dept/delete"})
public class DeptServlet extends HttpServlet {
    // 模板方法
    // 重写service方法（并没有重写doGet或者doPost）

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取servlet path
        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(request, response);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 根据部门编号删除部门
        String deptno = request.getParameter("deptno");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            // 开启事务（自动提交机制关闭）
            conn.setAutoCommit(false);
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
            // 事务提交
            conn.commit();
        } catch (SQLException e) {
            // 遇到异常要回滚
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
        // 判断删除成功还是失败
        if (count == 1) {
            // 删除成功
            // 删除成功，仍要跳转到列表页面，需要再次执行另一个servlet，使用转发或重定向
            request.getRequestDispatcher("/dept/list").forward(request, response); // 转发，地址栏地址不会改变
        } else {
            // 删除失败
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }


    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决请求体的中文乱码问题
        request.setCharacterEncoding("UTF-8");
        // 获取表单数据
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ?,loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
        if (count == 1) {
            // 更新成功 跳转到部门列表页面, 部门列表页面试Java动态生成的，所以需要再次执行另一个servlet
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            // 更新失败
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取部门信息
        // 注意乱码问题（tomcat10 不会出现这个问题）
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        // 连接数据库执行insert语句
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno,dname,loc) value(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
        if (count == 1) {
            // 保存成功跳转到列表页面
            // 转发是只有一次请求，之前是post，之后还是post
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            // 保存失败跳转到错误页面
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
