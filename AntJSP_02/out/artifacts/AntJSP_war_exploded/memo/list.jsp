<%@ page import="memo.MemoDAO" %>
<%@ page import="memo.MemoDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/14
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>한줄메모장</h2>
<form method="post" action="insert.jsp">
    이름 <input type="text" name="writer" size="10">
    메모 <input type="text" name="memo" size="30">
    <input type="submit" value="확인">
</form>
<%
    MemoDAO dao = new MemoDAO();
    List<MemoDTO> items = dao.list_memo();
%>
<table border = "1" width="700px">
    <tr>
        <th width="10%">번호</th>
        <th width="10%">이름</th>
        <th width="65%">메모</th>
        <th width="15%">날짜</th>
    </tr>
    <% for (MemoDTO dto : items){%>
    <tr>
        <td><%=dto.getIdx()%></td>
        <td><%=dto.getWriter()%></td>
        <td><%=dto.getMemo()%></td>
        <td><%=dto.getPost_date()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
