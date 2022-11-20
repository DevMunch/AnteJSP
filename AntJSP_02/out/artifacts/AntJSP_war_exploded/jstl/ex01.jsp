<%--
  1. JSTL(Jsp Standard Tag Library)
  - jsp 기본 태그 외의 표준 사용자정의 태그(Custom Tag) 변수 선언, 조건문, 반복문 등의 기능을 제공함
  2. JSTL download
  - jstl 1.25 다운로드 https://tomcat.apache.org/download-taglibs.cgi
  - jar 파일 4개를 다운로드한 후 lib에 복사
  3. JSTL의 사용 방법
  - 라이브러리들은 URI 형식으로 제공됨 태그에서 사용할 때는 접두어(prefix)를 사용
  - <%@ taglib prefix="접두어" uri="JSTL라이브러리의 URI"%> <접두어:태그>로 사용
  - <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <c:if ...></c:if>
  4. core 태그
  - uri : http://java.sun.com/jsp/jstl/core prefix : c
  제공 기능
  변수 선언, 조건문, 반복문 등
  - 변수 선언문 <c:set>
  조건문
  - <c:if test="조건식"></c:if>
  다중조건문 <c:choose>
  - <c:when> 조건에 맞는 경우 <c:otherwise> 나머지 경우
  반복문
  - <c:forEach var="개별변수" items="집합" begin="시작" end="종료">
  출력문
  - <c:out> 화면 출력. JSP의 표현식 대체
  다른 페이지로 이동
  - <c:redirect> response.sendRedirect()를 대체하는 태그

  5. format 태그
  - uri : http://java.sun.com/jsp/jstl/fmt prefix : fmt
  - 주요 기능 : 날짜, 숫자의 출력 형식 제공 숫자 형식 : <fmt:formatNumber> 날짜 형식 : <fmt:formatDate>
  6. functions 태그
  - uri : http://java.sun.com/jsp/jstl/functions prefix : fn
  - 주요 기능 : 다양한 함수 제공
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="ex01_result.jsp">
  number: <input type="number" name="num">
  <input type="submit" value="OK">
</form>
</body>
</html>
