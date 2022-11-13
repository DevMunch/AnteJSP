<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/13
  Time: 6:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String name= URLEncoder.encode("김철수", "utf-8");
  response.sendRedirect("redirect_result.jsp?name="+name+"&age=20");
%>
</body>
</html>
