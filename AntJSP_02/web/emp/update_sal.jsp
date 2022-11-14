<%@ page import="emp.EmpSalaryDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  int empno = Integer.parseInt(request.getParameter("empno"));
  EmpSalaryDAO dao = new EmpSalaryDAO();
  dao.update_sal(empno);
%>
처리되었습니다.<br>
<a href="list.jsp">사원목록으로 이동</a>
</body>
</html>
