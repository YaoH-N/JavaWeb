<%@page contentType="text/html;charset=utf-8" %>
<%--访问jsp的时候不生成session对象--%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎使用OA系统</title>
</head>
<body>

<h1>用户登录</h1>
<hr>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    username: <input type="text" name="username" autocomplete="off"><br>
    password:<input type="password" name="password" autocomplete="off"><br>
    <label for="flag">
        <input type="checkbox" id="flag" name="flag" value="1"/>十天内免登录
    </label>
    <input type="submit" value="login">
</form>

</body>
</html>
