package com.ityaohui.oa.web.action;

import com.ityaohui.oa.bean.Dept;
import com.ityaohui.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 小牛
 * Date: 2025/3/24 23:28
 * Description:
 */
@WebServlet({"/dept/list", "/dept/detail", "/dept/delete", "/dept/save", "/dept/edit"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取session，这个session是不需要新建的，只是获取当前session，获取不到放回null
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("username") != null) {
        String servletPath = request.getServletPath();
        String contextPath = request.getContextPath();
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/dept/edit".equals(servletPath)) {
            doModify(request, response);
        }
//        } else {
//            // 跳转到登录界面
//            response.sendRedirect(request.getContextPath() + "/index.jsp");
//        }


    }

    /**
     * 修改部门
     *
     * @param request
     * @param response
     * @throws IOException
     */
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
//            request.getRequestDispatcher("/dept/list").forward(request, response);
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/dept/list");
        }
    }

    /**
     * 保存部门信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
//            request.getRequestDispatcher("/dept/list").forward(request, response);
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/dept/list");
        }
    }

    /**
     * 根据部门编号删除部门信息
     *
     * @param request
     * @param response
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        Dept dept = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 删除成功 重定向到列表页面
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/dept/list");
        }
    }

    /**
     * 按照部门编号获取部门详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取部门编号
        String deptno = request.getParameter("deptno");
        // 根据部门编号获取部门信息，将部门信息封装成咖啡豆
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dept dept = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            // 这个结果集中只有一条数据，不需要while循环
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept = new Dept();
                dept.setDname(dname);
                dept.setLoc(loc);
                dept.setDeptno(deptno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 存储到request域中即可
        request.setAttribute("dept", dept);

        // 转发（不是重定向，因为要跳转到jsp页面做展示）
        // request.getRequestDispatcher("/detail.jsp").forward(request, response);
        String flag = request.getParameter("flag");
//        if("m".equals(flag)){
//             request.getRequestDispatcher("/edit.jsp").forward(request, response);
//        }else if("d".equals(flag)){
//            request.getRequestDispatcher("/detail.jsp").forward(request, response);
//        }

        request.getRequestDispatcher("/" + flag + ".jsp").forward(request, response);

    }

    /**
     * 连接数据库，查询所有的部门信息，将部门信息收集好，然后跳转到jsp做页面展示
     *
     * @param request
     * @param response
     */
    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 准备一个容器，用来专门存储部门
        List<Dept> depts = new ArrayList();
        // 连接数据库，查询所有的部门信息
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 将一个集合放在请求域中
        request.setAttribute("deptList", depts);
        // 转发，不要重定向
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
