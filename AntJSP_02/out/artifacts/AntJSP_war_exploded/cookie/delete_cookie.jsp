<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title> </head>
<body>
<%
  Cookie cookie = new Cookie("id", ""); cookie.setMaxAge(0); //즉시 삭제 response.addCookie(cookie);
%>
쿠키가 삭제되었습니다.
<a href="view_cookie.jsp">쿠키 확인</a> </body>
</html>
