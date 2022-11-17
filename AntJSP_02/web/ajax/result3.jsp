<%@ page import="keyword.KeywordDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/17
  Time: 6:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  KeywordDAO dao = new KeywordDAO();
  String keyword=request.getParameter("search");
  List<String> items=dao.list(keyword);
  for(String str : items){
    out.println(str+"<br>");
  }
%>
</body>
</html>
