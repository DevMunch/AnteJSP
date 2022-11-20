<%@ page import="member.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  MemberDTO dto = new MemberDTO();
  dto.setUserid("kim");
  dto.setName("김철수");
  dto.setPasswd("1234");
  request.setAttribute("dto",dto);
%>
<jsp:forward page="ex05_result.jsp"/>
</body>
</html>
