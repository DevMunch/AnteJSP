<%--
    1. Expression Language(표현언어)
    - JSP 페이지에서 자바 코드를 간단히 표현하는 방법 값이 null 이어도 예외가 발생하지 않음
    - ${변수 or 식}
    2. 변수의 사용 범위
    - 현재 페이지에서만 사용 pageContext ${pageScope.변수}
    - 요청페이지+응답페이지 request ${requestScope.변수}
    - 사용자 단위(로그인~로그아웃) session ${sessionScope.변수}
    - 서버 단위(모든 사용자) application ${applicationScope.변수}
    - 폼,쿼리스트링변수 request.getParameter ${param.변수}
    3. 객체의 표현
    - ${객체.변수명} ${sessionScope.세션변수명} ${param.변수명} ${paramValues.배열변수명}
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${10 + 5}<br>
${3 / 5}<br>
${7 mod 3}<br> <!-- 나머지 -->
${2 < 3}<br>
${10 >= 5 }<br>
</body>
</html>
