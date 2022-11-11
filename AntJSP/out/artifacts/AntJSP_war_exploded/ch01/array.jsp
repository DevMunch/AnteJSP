<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 3:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String[] fruits = {"apple","peach","grapes","orange"};
%>
<ul> <!-- 번호가 없는 리스트 -->
    <% for(String fruit : fruits){%>
        <li><%=fruit%></li>
    <%}%>
</ul>
</body>
</html>
