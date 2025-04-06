package com.ityaohui.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;
/**
 * Author: 小牛
 * Date: 2025/3/14 22:15
 * Description: JDBC工具类
 */
public class DBUtil {

    // 静态变量
    // 并且是有顺序的，自上而下的顺序
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String username = bundle.getString("username");
    private static String password = bundle.getString("password");

    static {
        // 注册驱动 注册驱动只需要注册一次，放在静态代码块中。类加载的时候执行）
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 获取连接
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void close(Connection conn, Statement ps, ResultSet rs){
        // 释放资源
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
