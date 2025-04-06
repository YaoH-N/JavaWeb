package com.ityaohui.javaweb.jsp.bean;

/**
 * Author: 小牛
 * Date: 2025/4/2 22:50
 * Description:
 */
public class User {
    private String username;
    private String password;
    private Integer age;
    public User(){

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
