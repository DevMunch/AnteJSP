<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
<body>
<%
    Cookie cookie = new Cookie("id", "김철수"); // 쿠키 객체 생성
    cookie.setMaxAge(10); // 쿠키의 유효시간을 초 단위로 지정, 0은 쿠키가 즉시 삭제, -1은 브라우저 종료시 삭제
    response.addCookie(cookie);// 쿠키 생성
    Cookie cookie2 = new Cookie("pwd", "1234");
    response.addCookie(cookie2);
%>
쿠키가 생성되었습니다.
<br>
<a href="view_cookie.jsp?name=김철수">쿠키 확인</a> </body>
</html>
