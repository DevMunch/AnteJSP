<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int num=Integer.valueOf(request.getParameter("num"));
    for(int i=1; i<=9; i++){
        out.println(num+"x"+i+"="+num*i+"<br>");
    }
%>
</body>
</html>
