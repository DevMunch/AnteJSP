<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 8:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  request.setCharacterEncoding("utf-8");
  String name = request.getParameter("name");
  String color = request.getParameter("color");
  String str = "";

  // A.equals(B) A와 B의 내용이 같으면 TRUE
  // a==b a,b문자열의 주소값을 비교한다. 그러므로 equals로 비교해야한다.
  if(color.equals("blue")){
    str="파랑";
  }else if(color.equals("green")){
    str="초록";
  } else if(color.equals("red")){
    str="빨강";
  }
%>
<%=name%>님이 선택한 색상은 <%=str%>입니다.
<div style="background-color: <%=color%>; width: 300px; height: 300px;">i</div>
</body>
</html>
