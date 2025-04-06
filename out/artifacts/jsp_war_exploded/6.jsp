<%@page contentType="text/html; charset=utf-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty param.username}">
    <h1>用户名不能为空。</h1>
</c:if>

<%--if 标签的var属性，不是必须的，存储的是test属性的值，true或false--%>
<%--if 标签的scope属性，用来指定var的存储与，也不是必须的--%>
<%--scope有四个值可选：page(pageContext)、 request、session、application--%>
<c:if test="${not empty param.username}" var="v" scope="request">
    <h1>欢迎你${param.username}</h1>
</c:if>

<hr>
${v}