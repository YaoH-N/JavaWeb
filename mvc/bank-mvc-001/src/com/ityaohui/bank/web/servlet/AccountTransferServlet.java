package com.ityaohui.bank.web.servlet;

import com.ityaohui.bank.exceptions.AppException;
import com.ityaohui.bank.exceptions.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Author: 小牛
 * Date: 2025/4/6 17:15
 * Description: 在不使用mvc架构模式的前提下，完成银行账户转账。
 * 分析这个程序存在哪些问题
 */

/**
 * 在不使用mvc架构模式的前提下，完成银行账户转账。
 * 分析这个程序存在哪些问题？
 * 缺点1：代码的复用性太差。（代码不能重复使用）例如查余额的操作
 * 原因：没有进行“职能分工”，没有独立组件的概念，所以没有办法进行代码复用。代码和代码之间的耦合度太高，扩展里太差。
 * 缺点2：耦合度高导致代码很难扩展。
 * 缺点3：操作数据库的代码和业务逻辑混杂在一起，编写代码很容易出错，无法专注业务逻辑的处理。
 *
 * <p>
 * 分析以下AccountTransferServlet他都负责了什么？
 * 1> 负责了接收数据
 * 2> 负责了业务逻辑处理
 * 3> 负责了数据库表中数据的crud操作（create【增】 retrieve【查】 update【改】 delete【删】
 * 4> 负责了页面的数据展示  out.print();
 */
@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取响应流
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取转账的相关信息
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        Double money = Double.parseDouble(request.getParameter("money"));

        // 编写转账的业务逻辑代码，连接数据库，尽心转账操作
        // 1. 转账之前要判断转出账户的余额是否充足
        ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);

            String sql = "select balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fromActno);
            rs = ps.executeQuery();
            // 处理结果集合
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < money) {
                    // 余额不足(使用异常处理机制。)
                    throw new MoneyNotEnoughException("对不起，余额不足！");
                }
                // 程序能够执行到这里，余额肯定充足
                // 开启事务（不要自动提交，改为手动提交，业务完成之后在提交。）
                conn.setAutoCommit(false);
                // 开始转账
                String sql2 = "update t_act set balance = balance - ? where actno = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setDouble(1, money);
                ps2.setString(2, fromActno);
                int count = ps2.executeUpdate();

//                String s = null;
//                s.toString();

                String sql3 = "update t_act set balance = balance + ? where actno = ?";
                ps3 = conn.prepareStatement(sql3);
                ps3.setDouble(1, money);
                ps3.setString(2, toActno);
                // 累计，都更新成功，count值就是2
                count += ps3.executeUpdate();
                if (count != 2) {
                    new AppException("App异常，请联系管理员！");
                }
                // 手动提交事务
                conn.commit();
                out.print("转账成功！");
            }
        } catch (Exception e) {
            // 出现异常就回滚事务
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            // 异常处理，发生异常后，进行的操作
            out.print(e.getMessage());
        } finally {
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
