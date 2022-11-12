<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 한글 인코딩 설정
    request.setCharacterEncoding("utf-8");
    // request 내장객체, 클라이언트의 요청 사항을 처리
    // request.getParameter("변수명") 폼데이터를 읽음
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
%>
이름:<%=name%><br>
성별:<%=gender%>
</body>
</html>
