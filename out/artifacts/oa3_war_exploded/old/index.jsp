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

<%-- 获取应用的根路径 --%>
<%--<% String contextPath = request.getContextPath();--%>
<%--    out.write(contextPath); %>--%>


<%--前端发送请求路径的时候，如果请求路径是绝对路径，要以 / 开始，加项目名 --%>
<%--    <a href="/oa/list.jsp">查看部门列表</a>--%>

<%-- 执行一个servlet，查询数据库，收集数据。 --%>
<%--<a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>--%>

<h1>用户登录</h1>
<hr>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    username: <input type="text" name="username" autocomplete="off"><br>
    password:<input type="password" name="password" autocomplete="off"><br>
    <label for="flag">
        <input  type="checkbox" id="flag" name="flag" value="1"/>十天内免登录
    </label>
    <input type="submit" value="login">
</form>

</body>
</html>
