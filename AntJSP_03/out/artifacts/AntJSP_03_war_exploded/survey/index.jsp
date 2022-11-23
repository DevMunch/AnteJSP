<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/22
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    response.sendRedirect(request.getContextPath()+"/survey_servlet/input.do");
%>
</body>
</html>
