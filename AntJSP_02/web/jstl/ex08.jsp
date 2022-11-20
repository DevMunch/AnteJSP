<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="str" value="hello jsp" />
문자열의 길이: ${fn:length(str)}<br>
키워드의 인덱스: ${fn:indexOf(str, 'jsp')}<br>
문자열 내용 변경: ${fn:replace(str, 'jsp', 'java')}<br>
</body>
</html>
