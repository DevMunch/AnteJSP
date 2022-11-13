<%@ page import="book.BookDAO" %>
<%@ page import="book.BookDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/14
  Time: 4:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  BookDAO dao=new BookDAO();
  List<BookDTO> items = dao.list_book();
%>
<table border="1">
  <tr>
    <th>idx</th>
    <th>title</th>
    <th>author</th>
    <th>price</th>
    <th>amount</th>
  </tr>
  <% for(BookDTO dto : items){ %>
  <tr>
    <td><%=dto.getIdx()%></td>
    <td><%=dto.getTitle()%></td>
    <td><%=dto.getAuthor()%></td>
    <td><%=dto.getPrice()%></td>
    <td><%=dto.getAmount()%></td>
  </tr>
  <%}%>
</table>
</body>
</html>
