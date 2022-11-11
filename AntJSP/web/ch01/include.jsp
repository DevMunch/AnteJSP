<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 3:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="common.Constants"%>
<html>
<head>
    <title>Title</title>
    <%@include file="sub.jsp"%>
    <style>
        body{
            background-color: <%=background%>;
        }
    </style>
</head>
<body>
<h2>max: <%=Constants.MAX%></h2>
<h2>msg: <%=msg%></h2>
</body>
</html>
