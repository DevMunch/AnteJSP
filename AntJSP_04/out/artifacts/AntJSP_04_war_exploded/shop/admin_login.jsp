<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <c:if test="${param.message == 'error'}">
    <script>
      alert("아이디 또는 비밀번호가 일치하지 않습니다.");
    </script>
  </c:if>
  <c:if test="${param.message == 'logout'}">
    <script>
      alert("로그아웃되었습니다.");
    </script>
  </c:if>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %> <h2>관리자 로그인</h2>
<form method="post" name="form1" action="/munch/admin_servlet/login.do">
  <table border="1">
    <tr>
      <td>아이디</td>
      <td><input name="userid"></td>
    </tr>
    <tr>
      <td>비밀번호</td>
      <td><input type="password" name="passwd"></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="로그인">
      </td>
    </tr>
  </table>
</form>
</body>
</html>