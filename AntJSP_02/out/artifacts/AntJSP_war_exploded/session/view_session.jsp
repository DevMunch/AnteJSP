<%@ page import="java.util.Enumeration" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Enumeration<String> en = session.getAttributeNames();
  while (en.hasMoreElements()) {
    String key = en.nextElement();
    Object value = session.getAttribute(key);
    out.println("key:" + key+",");
    out.println("value:" + value + "<br>");
  }
  String id = (String) session.getAttribute("id");
  String passwd = (String) session.getAttribute("passwd");
  int age=0;
  if(session.getAttribute("age")!=null){
    age = (int)session.getAttribute("age");
  }
  Object a = session.getAttribute("age");
  double height=0;

  if(session.getAttribute("height")!=null){
    height = (double)session.getAttribute("height");
  }
%>
아이디 : <%=id%><br>
비번 : <%=passwd%><br>
나이 : <%=age%><br>
키 : <%=height%><br>
<a href="delete_session.jsp">세션 삭제</a> </body>
</html>
