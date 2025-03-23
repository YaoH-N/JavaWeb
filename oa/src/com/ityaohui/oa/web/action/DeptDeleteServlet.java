package com.ityaohui.oa.web.action; /**
 * Author: 小牛
 * Date: 2025/3/16 16:01
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
