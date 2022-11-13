<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Map<String, Object> map = (Map<String,Object>)request.getAttribute("map");
%>
이름: <%=map.get("name")%><br> <%--map.get(key) => value--%>
나이: <%=map.get("age")%><br>
성별: <%=map.get("gender")%><br>
취미: <%=map.get("hobby")%><br>
</body>
</html>
