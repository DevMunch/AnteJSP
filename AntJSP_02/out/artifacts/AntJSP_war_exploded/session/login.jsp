<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" name="form1" action="/session_servlet/login.do">
  <table border="1">
    <tr>
      <td>ID</td>
      <td><input name="userid"></td>
    </tr>
    <tr>
      <td>PASS</td>
      <td><input type="password" name="passwd"></td>
    </tr> <tr>
    <td colspan="2" align="center"><input type="submit" value="LOGIN"></td>
  </tr>
  </table>
</form>
<%
  String message="";
  if (request.getParameter("message") != null) {
    message=request.getParameter("message");
    if(message.equals("logout")){
%>
<div style="color: red;">logout completed</div>
<%
    } }
%>
</body>
</html>
