<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Integer count =(Integer)session.getAttribute("counter");
  int num=1;
  if(count == null) {
    count = 1;
  } else {
    num = count.intValue();
    num++;
    count = num;
  }
  session.setAttribute("counter", count); %>
<%=count.intValue()%>번째 방문입니다. </body>
</html>
