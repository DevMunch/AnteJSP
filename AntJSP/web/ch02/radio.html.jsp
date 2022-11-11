<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 3:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="radio.jsp">
  이름: <input type="text" name="name">
  성별:
  <input type="radio" name="gender" value="male"> male
  <input type="radio" name="gender" value="female"> female
  <input type="submit" value="OK">
</form>
</body>
</html>
