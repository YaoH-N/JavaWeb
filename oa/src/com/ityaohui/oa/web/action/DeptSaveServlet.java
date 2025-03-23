package com.ityaohui.oa.web.action; /**
 * Author: 小牛
 * Date: 2025/3/16 16:51
 * Description:
 */

import com.ityaohui.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
