<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Connection conn = null;
  try{
      String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/xe";
      String dbId="ora_user";
      String dbPass="1234";
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn= DriverManager.getConnection(jdbcUrl,dbId,dbPass);
      out.println("오라클에 접속되었습니다.");
  }catch (Exception e){
    out.println("오라클 접속 에러...");
    e.printStackTrace();
  }
%>
</body>
</html>
