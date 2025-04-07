package com.ityaohui.threadlocal;

/**
 * Author: 小牛
 * Date: 2025/4/7 16:48
 * Description:
 */
public class UserDao {
    public void insert() {
        Thread thread = Thread.currentThread();
        System.out.println(thread);

        Connection connection = DBUtil.getConnection();
        System.out.println("UserDao insert");
    }
}
