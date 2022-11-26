<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String context = request.getContextPath(); // 컨텍스트 패스
    response.sendRedirect(context+"/gb_servlet/list.do");
%>
</body>
</html>
