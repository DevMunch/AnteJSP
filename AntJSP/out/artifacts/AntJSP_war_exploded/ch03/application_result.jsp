<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/13
  Time: 6:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String message=(String)application.getAttribute("message");
  out.println("서버 변수:"+message);
%>
</body>
</html>
