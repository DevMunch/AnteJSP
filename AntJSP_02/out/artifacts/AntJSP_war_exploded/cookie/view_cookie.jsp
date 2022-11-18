<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<%
  Cookie[] cookies = request.getCookies();
  if (cookies != null) {
    for (int i = 0; i < cookies.length; i++) {
      out.println("key:" + cookies[i].getName()+",");
      out.println("value:"+ cookies[i].getValue() + "<br>");
    } }
  String name=request.getParameter("name");
%>
<a href="delete_cookie.jsp?name=<%=name%>">쿠키 삭제</a> <br>
<a href="update_cookie.jsp">쿠키 변경</a>
<br>
</body>
</html>
