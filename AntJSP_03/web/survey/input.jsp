<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>${dto.question}</h2>
<form method="post" action="/survey_servlet/insert.do">
  <input type="radio" name="num" value="1">${dto.ans1}<br>
  <input type="radio" name="num" value="2">${dto.ans2}<br>
  <input type="radio" name="num" value="3">${dto.ans3}<br>
  <input type="radio" name="num" value="4">${dto.ans4}<br>
  <!--문제번호-->
  <input type="hidden" name="survey_idx" value="${dto.survey_idx}">
  <input type="submit" value="OK">
</form>
</body>
</html>
