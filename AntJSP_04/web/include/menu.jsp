<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/munch/product_servlet/list.do">상품목록</a> |
<c:if test="${sessionScope.userid!=null}"><!--로그인 상태라면-->
  <a href="/munch/cart_servlet/list.do">장바구니</a> |
</c:if>
<div style="text-align: right;">
  <c:choose>
    <c:when test="${sessionScope.userid==null}"><!--로그아웃 상태라면-->
      <a href="/munch/shop/login.jsp">로그인</a> |
      <a href="/munch/shop/admin_login.jsp">관리자 로그인</a> |
    </c:when>
    <c:otherwise><!--로그인 상태라면-->
      ${sessionScope.name}님이 로그인중입니다.
      <a href="/munch/login_servlet/logout.do">로그아웃</a>
    </c:otherwise>
  </c:choose>
</div>
<hr>