<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/13
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/member_servlet/join.do" method="post">
  아이디 <input name="userid"><br>
  비번 <input type="passworrd" name="passwd"><br>
  이름 <input name="name"><br>
  주소 <input name="address" size="50"><br>
  전화 <input name="tel"><br>
  <input type="submit" value="확인">
</form>
</body>
</html>
