<%@ page import="com.ityaohui.javaweb.jsp.bean.User" %>
<%@page contentType="text/html;charset=utf-8" %>
<%
    User user = new User();
    user.setUsername("jackson");
    user.setPassword("123456");
    user.setAge(40);

    request.setAttribute("userObj", user);
%>

<%--1. EL表达式会自动从某个范围中取数据。2. 调用toString方法将其转换成字符串  3. 将其输出到浏览器--%>
${userObj}

<br>
<%--输出user对象的username属性--%>
${userObj.username}
<br>
${userObj.password}
<br>
${userObj.age}
