<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/19
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="check_session.jsp"%>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function() {
        $("#btnLogout").click(function() {
            location.href = "/session_servlet/logout.do";
        });
    });
    </script>
</head>
<body>
<h2><%=session.getAttribute("message")%></h2>
<%=session.getAttribute("userid")%>님이 접속중입니다. <br>
<button type="button" id="btnLogout">로그아웃</button>
</body>
</html>
