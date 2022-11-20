<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  List<String> items=(List<String>)request.getAttribute("items ");
  for(String str : items){
    out.println(str+"<br>");
  }
%>
<hr>
<c:forEach var="fruit" items="${items}">
  ${fruit}<br>
</c:forEach>
</body>
</html>
