<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
      $(function(){
        $("#btn_add").click(function(){
          location.href="/munch/shop/product_write.jsp";
        });
      });
    </script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품목록</h2>
<c:if test="${sessionScope.admin_userid != null }"> <!--관리자 계정으로 로그인 했다면-->
    <button type="button" id="btn_add">상품등록</button>
</c:if>

<table border="1" width="500px">
    <tr align="center">
        <th>상품코드</th> <th>&nbsp;</th> <th>상품명</th> <th>가격</th>
    </tr>
    <c:forEach var="row" items="${list}">
        <tr>
            <td>
                    ${row.product_code}
            </td>
            <td>
                <img src="/munch/images/${row.filename}"
                     width="100px" height="100px">
            </td>
            <td>
                <a href="/munch/product_servlet/detail.do?product_code=${row.product_code}">${row.product_name}</a>
                <c:if test="${sessionScope.admin_userid != null }">
                    <br>
                    <a href="/munch/admin_servlet/edit.do?product_code=${row.product_code}">[편집]</a>
                </c:if>
            </td>
            <td>
                <fmt:formatNumber value="${row.price}" pattern="#,###" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
