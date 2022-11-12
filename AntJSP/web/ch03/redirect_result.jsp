<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/13
  Time: 6:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String name = request.getParameter("name");
  String age = request.getParameter("age");
%>
이름: <%=name%><br>
나이: <%=age%><br>
</body>
</html>
