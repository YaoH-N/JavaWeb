package com.ityaohui.javaweb.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/3/7 13:33
 * Description:
 */
public class LoginServlet extends GenericServlet {

    // 有没有可能在LoginServlet子类中重写 init() 方法？？？？
    // 当然可能，重写init方法导致父类init方法不执行， config为null
    // 可以用final 关键字 强制父类 init 方法不允许被重写
//    public void init(ServletConfig config) throws ServletException {
//        System.out.println("重写自己的规则！！！");
//    }

    // 但是子类就是想重写init方法怎么办？？？


    @Override
    public void init() {
        System.out.println("LoginServlet's init method execute!");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("正在处理用户登录的请求，请稍后.....");

        // 想在LoginServlet子类中使用ServletConfig对象怎么办？
        ServletConfig config = this.getServletConfig();
        System.out.println("service方法中是否可以获取到ServletConfig对象？" + config);
    }
}
