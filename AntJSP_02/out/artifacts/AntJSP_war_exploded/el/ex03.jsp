<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  session.setAttribute("name", "김철수");
  session.setAttribute("age",20);
  session.setAttribute("job","dba");
%>
세션변수가 생성되었습니다.<br>
<a href="ex03_result.jsp">확인</a>
</body>
</html>
