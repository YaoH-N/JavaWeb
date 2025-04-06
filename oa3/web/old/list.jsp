<%@ page import="java.util.List" %>
<%@ page import="com.ityaohui.oa.bean.Dept" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
</head>
<body>

<script type="text/javascript">
    function del(dno) {
        // 弹出确认框，用户点击确定，返回true，点击取消返回false
        var ok = window.confirm("亲，删了不可恢复哦！");
        if (ok) {
            document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno=" + dno;
        }
    }
</script>

<h1 align="center">部门列表</h1>
<h2>欢迎<%=request.getSession().getAttribute("username")%>
</h2>
<a href="/oa/user/logout">退出系统</a>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>

    <%
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
        int i = 0; // 序号
        // 循环遍历
        for (Dept dept : deptList) {
            // 在后台输出
            // System.out.println(dept.getDeptno());
            // out.write(dept.getDname());
    %>
    <tr>
        <td><%=++i%>
        </td>
        <td><%=dept.getDeptno()%>
        </td>
        <td><%=dept.getDname()%>
        </td>
        <td>
            <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
            <a href="<%=request.getContextPath()%>/dept/detail?flag=edit&deptno=<%=dept.getDeptno()%>">修改</a>
            <a href="<%=request.getContextPath()%>/dept/detail?flag=detail&deptno=<%=dept.getDeptno()%>">详情</a>
        </td>
    </tr>

    <%
        }
    %>


    <%--    <tr>--%>
    <%--        <td>2</td>--%>
    <%--        <td>20</td>--%>
    <%--        <td>研发部</td>--%>
    <%--        <td>--%>
    <%--            <!--href后面设置为 javascript:void(0) 表示：仍然保留住超链接的样子-->--%>
    <%--            <!--点击此超链接之后，不进行页面的跳转。-->--%>
    <%--            <!--我只是希望用户点击该超链接的时候执行一段JS代码，不进行页面的跳转。-->--%>
    <%--            <a href="javascript:void(0)" onclick="del(20)">删除</a>--%>
    <%--            <a href="<%=request.getContextPath()%>/edit.jsp">修改</a>--%>
    <%--            <a href="<%=request.getContextPath()%>/detail.jsp">详情</a>--%>
    <%--        </td>--%>
    <%--    </tr>--%>
    <%--    <tr>--%>
    <%--        <td>3</td>--%>
    <%--        <td>30</td>--%>
    <%--        <td>运营部</td>--%>
    <%--        <td>--%>
    <%--            <a href="javascript:void(0)" onclick="del(30)">删除</a>--%>
    <%--            <a href="<%=request.getContextPath()%>/edit.jsp">修改</a>--%>
    <%--            <a href="<%=request.getContextPath()%>/detail.jsp">详情</a>--%>
    <%--        </td>--%>
    <%--    </tr>--%>
</table>

<hr>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>

</body>
</html>
