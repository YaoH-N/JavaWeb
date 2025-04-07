package com.ityaohui.threadlocal;

/**
 * Author: 小牛
 * Date: 2025/4/7 16:48
 * Description:
 */
public class UserService {
    UserDao userDao = new UserDao();

    public void save() {
        Thread thread = Thread.currentThread();
        System.out.println(thread);

        Connection connection = DBUtil.getConnection();
        userDao.insert();
    }
}
