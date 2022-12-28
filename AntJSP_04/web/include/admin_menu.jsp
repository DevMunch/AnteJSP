<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/munch/product_servlet/list.do">상품목록</a> |
<a href="/munch/shop/product_writer.jsp">상품등록</a> |
<c:choose>
  <c:when test="${sessionScope.admin_userid == null }">
    <a href="/munch/shop/admin_login.jsp">관리자 로그인</a> |
  </c:when>
  <c:otherwise>
    ${sessionScope.name}님이 로그인중입니다.
    <a href="/munch/admin_servlet/logout.do">로그아웃</a>
  </c:otherwise>
</c:choose>
<hr>