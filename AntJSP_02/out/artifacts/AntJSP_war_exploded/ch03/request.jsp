<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/ch03/request.do">
  이름: <input name="name"><br>
  나이: <input name="age"><br>
  성별: <input type="radio" name="gender" value="m">남
       <input type="radio" name="gender" value="f">여
       <br>
  취미: <select name="hobby">
        <option value="운동">운동</option>
        <option value="요리">요리</option>
        <option value="낚시">낚시</option>
        <option value="등산">등산</option>
       </select>
       <input type="submit" value="확인">
</form>
</body>
</html>
