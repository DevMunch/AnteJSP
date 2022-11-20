<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  int[] nums={10,70,80,50,40,30,20};
%>
<c:set var="numbers" value="<%=nums%>" />
<c:forEach var="num" items="${numbers}">
  ${num},
</c:forEach>
<br>
<c:set var="season" value="autumn" />
<c:choose>
  <c:when test="${season == 'spring'}">
    <img src="../images/spring.jpg">
  </c:when>
  <c:when test="${season == 'summer'}">
    <img src="../images/summer.jpg">
  </c:when>
  <c:when test="${season == 'autumn'}">

    <img src="../images/autumn.jpg">
  </c:when>
  <c:when test="${season == 'winter'}">
    <img src="../images/winter.jpg">
  </c:when>
  <c:otherwise>otherwise</c:otherwise>
</c:choose>
</body>
</html>
