package com.ityaohui.javaweb.servlet;

import jakarta.servlet.http.HttpServlet;

/**
 * Author: 小牛
 * Date: 2025/3/11 21:12
 * Description:
 */
public class HelloServlet extends HttpServlet {
    // 第一次请求，创建HelloServlet对象，通过无参数构造方法创建对象
    // public HelloServlet(){}
    // 没有init方法，必然会调用父类HttpServlet中的init方法。
    // 父类HttpServlet也没有init方法，会继续执行GenericServlet类中的init方法
}
