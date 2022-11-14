<%@ page import="memo.MemoDTO" %>
<%@ page import="memo.MemoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String writer = request.getParameter("writer");
    String memo = request.getParameter("memo");

    MemoDTO dto = new MemoDTO();
    dto.setWriter(writer);
    dto.setMemo(memo);
    // IP 주소를 얻어오는 메서드
    dto.setIp(request.getRemoteAddr());
    MemoDAO dao = new MemoDAO();

    dao.insertMemo(dto);
    response.sendRedirect("list.jsp");
%>
</body>
</html>
