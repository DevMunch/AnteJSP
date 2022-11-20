<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Date date=new Date();
  out.println(date+"<br>");
%>
<c:set var="date" value="<%=new Date()%>" />
${date}<br>
<fmt:formatDate value="${date}"/><br>
<fmt:formatDate value="${date}" type="time"/><br>
<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss "/><br>
<fmt:formatDate value="${date}" dateStyle="full"/><br>
<fmt:formatDate value="${date}" dateStyle="long"/><br>
<fmt:formatDate value="${date}" dateStyle="medium"/><br>
<fmt:formatDate value="${date}" dateStyle="short"/><br>

<c:set var="num" value="123456789" />
${num}<br>
<fmt:formatNumber value="${num}" groupingUsed="true"/><br>
</body>
</html>
