<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/13
  Time: 6:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String[] names={"protocol","server's name","method","context","uri","url","ip"};
  String[] values={request.getProtocol(), request.getServerName(),
          request.getMethod(), request.getContextPath(), request.getRequestURI(),
          request.getRequestURL().toString(),request.getRemoteAddr()};

  for(int i=0; i<names.length; i++){
    out.println(names[i] + ":" + values[i] + "<br>");
  }
  out.println("<hr>");
  Enumeration<String> en = request.getHeaderNames();
  String key="";
  String value="";
  while(en.hasMoreElements()){ // 다음 요소가 있는지?
    key=en.nextElement(); // 다음 요소의 키값을 가져온다.
    value=request.getHeader(key); // 다음 요소의 값을 가져온다.
    out.println(key+":"+values+"<br>"); // 출력
  }
%>
</body>
</html>
