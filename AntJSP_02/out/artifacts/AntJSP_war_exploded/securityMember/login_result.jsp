<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String result=(String)request.getAttribute("result");
  out.println(result);
%>
</body>
</html>
