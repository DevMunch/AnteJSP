<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 6:49 AM
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
  String str = request.getParameter("str");
  str = str.replace("<","&lt;"); // &lt; &gt;로 태그사용을 막는다
  str = str.replace(">","&gt;");
  str = str.replace("\n","<br>"); // 줄 바꿈 처리
  str = str.replace("  ","&nbsp;&nbsp;"); // 스페이스 2개를 바꾼다.
%>
입력한 내용: <br>
<%=str%>
</body>
</html>
