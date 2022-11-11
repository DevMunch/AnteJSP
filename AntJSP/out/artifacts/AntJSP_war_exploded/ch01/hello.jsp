<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% // 자바 코드 영역, scriptlet
    String str = "안녕하세요";
%>
<!-- = 변수 expression 표현식, 변수에 저장된 값을 html로 출력 -->
<h2>message: <%=str%></h2>
</body>
</html>
