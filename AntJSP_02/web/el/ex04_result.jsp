<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  HashMap<String,String> map = (HashMap<String,String>)request.getAttribute("map");
  String[] names={"kim","park","song","hyun"};
  for(String name:names){
    out.println(name+"=>"+map.get(name)+"<br>");
  }
%>
${map["kim"]}<br>
${map["park"]}<br>
${map["song"]}<br>
${map["hyun"]}<br>
${map.kim}<br>
${map.park}<br>
${map.song}<br>
${map.hyun}<br>
</body>
</html>
