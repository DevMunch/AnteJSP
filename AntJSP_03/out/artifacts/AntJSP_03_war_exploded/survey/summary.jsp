<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Summary</h2>
<table border="1">
  <tr align="center">
    <th>num</th>
    <th>count</th>
    <th>rate</th>
  </tr>
  <c:forEach var="dto" items="${list}">
    <tr>
      <td>${dto.num}</td>
      <td>${dto.sum_num}</td>
      <td>${dto.rate}%</td>
    </tr>
  </c:forEach>
</table>
<a href="/survey_servlet/input.do">Home</a>
</body>
</html>
