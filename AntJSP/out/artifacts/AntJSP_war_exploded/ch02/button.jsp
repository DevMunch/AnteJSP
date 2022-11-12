<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 6:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int price=Integer.parseInt(request.getParameter("price")); // parseInt는 int 반환
    int amount=Integer.valueOf(request.getParameter("amount"));// valueOf는 wrapper객체를 반환(Integer)
    int money = price * amount;
%>
가격: <%=price%><br>
수량: <%=amount%><br>
금액: <%=money%>
</body>
</html>
