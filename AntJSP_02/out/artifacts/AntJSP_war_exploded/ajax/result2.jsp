<%@ page import="ajax.MemberDAO" %>
<%@ page import="ajax.MemberDTO" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/17
  Time: 4:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String id=request.getParameter("id");
  String pwd=request.getParameter("pwd");
  MemberDAO dao=new MemberDAO();
  MemberDTO dto=new MemberDTO();
  dto.setUserid(id);
  dto.setPasswd(pwd);
  String result = dao.login(dto);
  if(result != null) {
    out.println(result+"님 환영합니다."); }else{
    out.println("아이디 또는 비밀번호가 일치하지 않습니다."); }
%>
</body>
</html>
