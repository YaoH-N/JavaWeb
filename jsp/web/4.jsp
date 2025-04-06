<%@page contentType="text/html; utf-8" %>
<%
    String name="jack";
    // 输出name到浏览器
    System.out.println("name = "+name);

    out.write("name="+name);
%>