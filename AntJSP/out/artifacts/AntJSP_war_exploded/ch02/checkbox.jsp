<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 3:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // getParameterValues("배열변수명")
    String[] fruits=request.getParameterValues("fruits");
    // 하나도 체크 안하면 => null
    if(fruits!=null){
        for(String fruit : fruits){
            out.println(fruit+"<br>");
        }
    }
%>
</body>
</html>
