<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%
  ArrayList<String> items=new ArrayList<String>();
  items.add("orange");
  items.add("apple");
  items.add("melon");
  items.add("peach");
  request.setAttribute("items", items);
%>
<jsp:forward page="ex02_result.jsp" />
</body>
</html>
