<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date"%>
<%@ page import="common.MyCookie"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<%
  String count = MyCookie.getCookie(request.getCookies(), "cou nt");
  int int_count = 0;
  Date date = new Date();
  long now_time = date.getTime();
  String str_visit = MyCookie.getCookie(request.getCookies(), "visit_time");
  long visit_time = 0;
  if (str_visit != null && !str_visit.equals("")) {
    visit_time = Long.parseLong(str_visit);
  }
  if (count == null || count.equals("")) { response.addCookie(new Cookie("count", "1")); response.addCookie(new Cookie("visit_time", Long.toString
          (now_time)));
    int_count=1;

  } else {
    long period = now_time - visit_time;
    //out.println("now:" + now_time + "<br>");
    //out.println("previous:" + visit_time + "<br>");
    //out.println("difference:" + period + "<br>");
    int_count = Integer.parseInt(count);
    if (period > 3 * 1000) {
      int_count++;
      response.addCookie(new Cookie("count", Integer.toString(int_count)));
      response.addCookie(new Cookie("visit_time", Long.toString(now_time)));
    } }
%>
<%=int_count%>번째 방문입니다. </body>
</html>