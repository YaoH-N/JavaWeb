package com.ityaohui.javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;

/**
 * Author: 小牛
 * Date: 2025/3/24 12:59
 * Description:
 */
public class ReflectAnnotation {
    public static void main(String[] args) throws Exception {
        // 使用反射机制将类上面的注解进行解析
        // 获取类Class对象
        Class<?> welcomServletClass = Class.forName("com.ityaohui.javaweb.servlet.WelcomeServlet");

        // 获取这个类上面的注解对象
        // 先判断这个类上面有没有这个注解对象，如果有这个对象，就获取该注解对象
        boolean annotationPresent = welcomServletClass.isAnnotationPresent(WebServlet.class);
        if (annotationPresent) {
            WebServlet annotation = welcomServletClass.getAnnotation(WebServlet.class);
            // 获取注解的value属性值
            String[] value = annotation.value();
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }
        }
        System.out.println(annotationPresent);
    }
}
