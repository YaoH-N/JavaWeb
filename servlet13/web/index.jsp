<%--
  Created by IntelliJ IDEA.
  User: 24243
  Date: 2025/3/31
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>cookie</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/cookie/generate">服务器生成cookie，然后将cookie相应给浏览器，浏览器接收cookie，将cookie放到客户端上</a><br>
<a href="<%=request.getContextPath()%>/sendCookie">浏览器发送cookie给服务器</a>
</body>
</html>
