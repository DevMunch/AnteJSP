<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int a = Integer.parseInt(request.getParameter("num"));
%>
a:<%=a%>
</body>
</html>
