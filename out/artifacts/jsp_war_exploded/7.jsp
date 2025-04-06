<%@ page import="com.ityaohui.javaweb.jsp.bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--var用来指定循环中的变量--%>
<%--begin开始--%>
<%--end结束--%>
<%--step步长--%>
<%--底层实际上:会将i存储到pageContext域中,--%>
<c:forEach var="i" begin="1" end="10" step="1">
    ${i}<br>
</c:forEach>
<hr>


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
<%--使用core标签库中forEach标签。对List集合进行遍历--%>
<%--varStatus="这个属性表示var的状态对象,这里是一个java对象,这个java对象代表了 var的状态"--%>
<c:forEach items="${uList}" var="u" varStatus="userStatus">
    编号: ${userStatus.count}, username: ${u.username}, password: ${u.password}<br>
</c:forEach>