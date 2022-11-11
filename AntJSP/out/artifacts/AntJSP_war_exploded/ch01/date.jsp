<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 2:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Date nowDate = new Date();
  out.println(nowDate + "<br>");
  // 날짜를 스트링 변환
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a HH:mm:ss");
  String formatDate = dateFormat.format(nowDate);
%>
현재 날짜는 <%=formatDate%>입니다.
</body>
</html>
