<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/13
  Time: 6:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String info=application.getServerInfo();
  application.log("WAS:"+info); // 톰캣 정보, 콘솔
  out.println("WAS:"+info+"<br>"); // 톰캣 정보, 브라우저
  String path=application.getRealPath("/");
  application.log("서비스 경로:"+path);
  out.println("서비스 경로:"+path+"<br>");
  application.setAttribute("message","hello");
%>
<a href="application_result.jsp">서버 변수 확인</a>
</body>
</html>
