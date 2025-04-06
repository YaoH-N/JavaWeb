<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 想request作用域中存储username为zhangsan
    request.setAttribute("username", "zhangsan");
%>
<%--将request域中的数据取出来，并且还要输出到浏览器，使用java代码怎么办？--%>
<%=request.getAttribute("username")%>

<%--使用EL表达式--%>
${username}