<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#btnSave").click(function(){ //버튼 클릭
		var writer=$("#writer").val(); //태그에 입력한 값
		var subject=$("#subject").val();
		var contents=$("#contents").val();
		var passwd=$("#passwd").val();
		if(writer==""){
			alert("이름을 입력하세요.");
			$("#writer").focus();
			return;
		}
		if(subject==""){
			alert("제목을 입력하세요.");
			$("#subject").focus();
			return;
		}
		if(contents==""){
			alert("내용을 입력하세요.");
			$("#contents").focus();
			return;
		}
		if(passwd==""){
			alert("비번을 입력하세요.");
			$("#passwd").focus();
			return;
		}				
		document.form1.submit();
	});
});
</script>
</head>
<body>
<h2>답변쓰기</h2>
<form name="form1" method="post" 
action="/munch/board_servlet/insert_reply.do">
<table border="1" width="700px">
	<tr>
		<td align="center">이름</td>
		<td><input name="writer" id="writer"></td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td><input name="subject" id="subject" size="60" value="${dto.subject}"></td>
	</tr>
	<tr>
		<td align="center">본문</td>
		<td><textarea rows="5" cols="60" name="contents" id="contents">${dto.contents}</textarea></td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td><input type="password" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<!--부모글 번호-->
			<input type="hidden" name="num" value="${dto.num}">
			<input type="button" value="확인" id="btnSave">
		</td>
	</tr>
</table>
</form>
</body>
</html> 