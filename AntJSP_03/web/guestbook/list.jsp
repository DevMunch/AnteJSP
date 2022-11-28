<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script>
      function gb_search(){
        document.form1.action="/gb_servlet/list.do";
        document.form1.submit();
      }
    </script>
</head>
<body>
<h2>방명록</h2>
<form name="form1" method="post">
  <select name="searchkey">
    <c:if test="${searchkey=='name'}">
      <option value="name" selected>이름</option>
      <option value="contents">내용</option>
      <option value="name_contents">이름+내용</option>
    </c:if>
    <c:if test="${searchkey=='contents'}">
      <option value="name">이름</option>
      <option value="contents" selected>내용</option>
      <option value="name_contents">이름+내용</option>
    </c:if>
    <c:if test="${searchkey=='name_contents'}">
      <option value="name">이름</option>
      <option value="contents">내용</option>
      <option value="name_contents" selected>이름+내용</option>
    </c:if>
  </select>
  <input type="text" name="search" value="${search}">
  <input type="button" value="조회" onclick="gb_search()">
</form>
${count}개의 글이 있습니다.&nbsp;
<input type="button" value="글쓰기" onclick="location.href='/guestbook/write.jsp'">
<c:forEach var="dto" items="${list}">
<form action="/gb_servlet/passwd_check.do" method="post">
<!--글번호-->
<input type="hidden" name="idx" value="${dto.idx}">
<table border="1" style="width: 600px">
    <tr>
      <td style="text-align: center;background: cyan;width: 20%">이름</td>
      <td style="text-align: center;width: 30%">${dto.name}</td>
      <td style="text-align: center;background: cyan;width: 20%">날짜</td>
      <td style="text-align: center;width: 30%">${dto.post_date}</td>
    </tr>
    <tr>
      <td style="text-align: center;background: cyan;width: 20%">이메일</td>
      <td colspan="3">${dto.email}</td>
    </tr>
    <tr>
      <td colspan="4">${dto.contents}</td>
    </tr>
    <tr>
      <td colspan="4">
        비밀번호 <input type="password" name="passwd">
        <input type="submit" value="수정/삭제">
      </td>
    </tr>
</table>
</form>
</c:forEach>
</body>
</html>
