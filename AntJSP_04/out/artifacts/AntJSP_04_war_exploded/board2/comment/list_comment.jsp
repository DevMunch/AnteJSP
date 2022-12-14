<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="700px">
<c:forEach var="row" items="${list}"> <!-- 댓글 리스트를 반복하며 출력 -->
	<tr>
		<td>
			${row.writer} <!--댓글 작성자-->
			( <fmt:formatDate value="${row.reg_date}" 
				pattern="yyyy-MM-dd HH:mm:ss"/> )<br><!--댓글 시간-->
			${row.contents} <!--댓글 내용-->
		</td>
	</tr>
</c:forEach>
</table>
</body>
</html>