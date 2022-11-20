<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="i" begin="2"  end="9">
  <h2>Table ${i}</h2>
  <c:forEach var="j" begin="1" end="9">
    ${i} x ${j} = ${i * j}<br>
  </c:forEach>
</c:forEach>
</body>
</html>
