<%@ page import="emp.EmpSalaryDAO" %>
<%@ page import="emp.EmpDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>사원목록</h2>
<table border="1">
  <tr>
    <th>사번</th>
    <th>이름</th>
    <th>직급</th>
    <th>입사일자</th>
    <th>급여</th>
    <th>&nbsp;</th>
  </tr>
  <%
    EmpSalaryDAO dao = new EmpSalaryDAO();
    List<EmpDTO> items = dao.list_emp();
    for(EmpDTO dto : items){
  %>
  <tr>
    <td><%=dto.getEmpno()%></td>
    <td><%=dto.getEname()%></td>
    <td><%=dto.getJob()%></td>
    <td><%=dto.getHiredate()%></td>
    <td><%=dto.getSal()%></td>
    <td><input type="button" onclick="location.href='update_sal.jsp?empno=<%=dto.getEmpno()%>'" value="급여인상"></td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>
