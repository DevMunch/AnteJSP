<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<%
  response.addCookie(new Cookie("id", "park")); %>
쿠키가 수정되었습니다.
<br>
<a href="view_cookie.jsp">쿠키 확인</a> </body>
</html>
