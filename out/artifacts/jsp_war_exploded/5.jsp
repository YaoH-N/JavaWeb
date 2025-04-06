<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ityaohui.javaweb.jsp.bean.User" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html;charset=utf-8" %>
<%--引入标签库 prefix 随便写，就是一个标识  core jstl核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<User> userList = new ArrayList<>();
    User u1 = new User("zhangsan", "123456");
    User u2 = new User("lisi", "123456");
    User u3 = new User("wangwu", "123456");

    userList.add(u1);
    userList.add(u2);
    userList.add(u3);

    // 将list集合存储到request域中
    request.setAttribute("uList", userList);

%>

<%--需求：将List集合中的元素遍历。输出学生信息到浏览器--%>
<%--使用java代码--%>
<%
    List<User> uList = (List<User>) request.getAttribute("uList");
    // 编写for循环遍历list集合
    for (User user : uList) {
%>
username: <%=user.getUsername()%>, password: <%=user.getPassword()%> <br>
<%
    }
%>
<hr>
<%--使用core标签库中forEach标签。对List集合进行遍历--%>
<c:forEach items="${uList}" var="u">
    username: ${u.username},password: ${u.password}<br>
</c:forEach>