<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/15
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>회원가입(암호화 방식)</h2>
<form method="post" action="/encrypt_servlet/join_encrypt.do">
    아이디 : <input name="userid"><br>
    비밀번호 : <input type="password" name="passwd"><br>
    이름 : <input name="name"><br>
    <button>회원가입</button>
</form>
</body>
</html>
