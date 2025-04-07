package com.ityaohui.threadlocal;

/**
 * Author: 小牛
 * Date: 2025/4/7 16:48
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread);

        // 获取Connection对象
        Connection connection = DBUtil.getConnection();
        // 调用service
        UserService userService = new UserService();
        userService.save();
    }
}
