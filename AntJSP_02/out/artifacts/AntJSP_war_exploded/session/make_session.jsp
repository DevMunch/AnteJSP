<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String id = "kim@naver.com";
    String passwd = "1234";
    int age = 20;
    double height = 170.5;
    session.setAttribute("id", id);
    session.setAttribute("passwd", passwd);
    session.setAttribute("age", age);
    session.setAttribute("height", height);
    out.println("저장되었습니다.");
%>
<a href="view_session.jsp">세션 확인</a> </body>
</html>
