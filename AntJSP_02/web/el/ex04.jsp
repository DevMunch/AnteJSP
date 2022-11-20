<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Map<String, String> map=new HashMap<>();
  map.put("kim","김철수");
  map.put("park","박희정");
  map.put("song","송영미");
  map.put("hyun","현정수");
  request.setAttribute("map", map);
%>
<jsp:forward page="ex04_result.jsp"/>
</body>
</html>
