<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 7:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String name=request.getParameter("name");
    int age=Integer.parseInt(request.getParameter("age"));
    String str = "name:"+name+",age:"+age;
    out.println(str);
%>
</body>
</html>
