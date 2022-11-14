<%@ page import="emp.EmpDAO" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/14
  Time: 5:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  EmpDAO dao = new EmpDAO();
  dao.insert();
  dao.insert_batch();
  out.println("트랜잭션 처리가 완료되었습니다.");
%>
</body>
</html>
