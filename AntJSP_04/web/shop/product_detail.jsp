<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../include/menu.jsp"%>
<h2>상품정보</h2>
<table>
  <tr>
    <td>
      <img src="/munch/images/${dto.filename}" width="300px" height="300px">
    </td>
    <td>
      <table>
        <tr>
          <td>상품명</td>
          <td>${dto.product_name}</td>
        </tr>
        <tr>
          <td>가격</td>
          <td>${dto.price}</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>${dto.description}</td>
        </tr>
        <tr>
          <td colspan="2">
            <form name="form1" method="post" action="/munch/cart_servlet/insert.do">
              <!--  상품코드  -->
              <input type="hidden" name="product_code" value="${dto.product_code}">
              <select name="amount">
                <c:forEach begin="1" end="10" var="i">
                  <option value="${i}">${i}</option>
                </c:forEach>
              </select>&nbsp;개
              <input type="submit" value="장바구니에 담기">
            </form>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
