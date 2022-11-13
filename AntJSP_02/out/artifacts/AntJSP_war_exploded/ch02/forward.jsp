<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  request.setAttribute("id","kim");
  request.setAttribute("name","김철수");
//  RequestDispatcher rd = request.getRequestDispatcher("/ch02/forward_result.jsp");
//  rd.forward(request, response);
%>
<!-- 액션태그 -->
<jsp:forward page="forward_result.jsp"></jsp:forward>

</body>
</html>
