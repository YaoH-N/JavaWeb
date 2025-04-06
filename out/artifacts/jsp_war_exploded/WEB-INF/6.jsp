<%@page contentType="text/html; charset=utf-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty param.username}">
<h1>用户名不能为null</h1>
</c:if>