<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  for(int i=6; i>=1; i--){
    // out 내장객체, 웹 브라우저에 html 출력
    out.println("<h"+i+">Heading</h"+">");
  }
%>
</body>
</html>
