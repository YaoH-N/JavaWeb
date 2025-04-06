<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
    <!-- 设置整个网页的基础路径是：http://localhost:8080/oa/  -->
    <base href="http://localhost:8080/oa/">
</head>
<body>
<h2>欢迎${user.username},在线人数${onlinecount}人
</h2>
<a href="user/logout">退出系统</a>


<script type="text/javascript">
    function del(dno) {
        // 弹出确认框，用户点击确定，返回true，点击取消返回false
        var ok = window.confirm("亲，删了不可恢复哦！");
        if (ok) {
            document.location.href = "${pageContext.request.contextPath}/dept/delete?deptno=" + dno;
        }
    }
</script>

<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${deptList}" varStatus="deptStatus" var="dept">
        <tr>
            <td>${deptStatus.count}
            </td>
            <td>${dept.deptno}
            </td>
            <td>${dept.dname}
            </td>
            <td>
                <a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
                <a href="dept/detail?flag=edit&deptno=${dept.deptno}">修改</a>
                <a href="dept/detail?flag=detail&deptno=${dept.deptno}">详情</a>
            </td>
        </tr>
    </c:forEach>
</table>

<hr>
<a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>

</body>
</html>
