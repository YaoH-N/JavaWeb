package com.ityaohui.threadlocal;

import java.util.Map;

/**
 * Author: 小牛
 * Date: 2025/4/7 17:04
 * Description:
 */
public class DBUtil {

    // 全局的大Map集合
    private static MyThreadLocal<Connection> local = new MyThreadLocal<>();

    /**
     * 每次都从这个方法来获取Connection对象
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = local.get();
        if (connection == null) {
            // 创建新的Connection对象，并存入大Map中
            connection = new Connection();
            local.set(connection);
        }

        return connection;
    }
}
