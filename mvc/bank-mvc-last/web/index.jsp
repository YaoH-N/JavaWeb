<%--
  Created by IntelliJ IDEA.
  User: 24243
  Date: 2025/4/6
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>银行账户转账</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<form action="transfer" method="post">
    转出账户：<input type="text" name="fromActno"><br>
    转入账户：<input type="text" name="toActno"><br>
    转账金额：<input type="text" name="money"><br>
    <input type="submit" value="转账">
</form>
</body>
</html>
