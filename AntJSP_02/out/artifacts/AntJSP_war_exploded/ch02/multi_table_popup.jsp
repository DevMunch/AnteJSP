<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/12
  Time: 7:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function winclose() {
            // opener는 부모를 창을 말한다.
            opener.document.form1.num.value = document.form1.num.value;
            window.close();
        }
    </script>
</head>
<body>
<%
  int num=Integer.parseInt(request.getParameter("num"));
%>
<h2><%=num%> 단</h2>
<%
    for(int i=1; i<=9; i++){
    out.println(num+"x"+i+"="+num*i+"<br>");
    }
%>
<form name="form1">
    메인창에 전달할 값
    <input type="text" name="num">
    <input type="button" value="닫기" onclick="winclose()">
</form>
</body>
</html>
