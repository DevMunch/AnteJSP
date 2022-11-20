<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  int num=Integer.parseInt(request.getParameter("num"));
  int sum=0;
  for(int i=1; i<=num; i++){
    sum += i;
    out.println("합계:"+sum);
  }
%>
<hr>
<c:set var="sum" value="0" />
<c:forEach var="i" begin="1" end="${param.num}">
  <c:set var="sum" value="${sum + i}" />
</c:forEach>
합계:${sum}
</body>
</html>
