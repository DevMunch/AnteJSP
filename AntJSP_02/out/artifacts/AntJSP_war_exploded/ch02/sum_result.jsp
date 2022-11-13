<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  int sum=(Integer)request.getAttribute("sum");
  out.println(sum);
%>
</body>
</html>
