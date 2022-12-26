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
	$("#btnSave").click(function(){ // 아이디가 #btnSave인 버튼 클릭
		// 자바스크립트로 찾을때는 태그의 id로 찾는다.
		var writer=$("#writer").val(); //태그에 입력한 값
		var subject=$("#subject").val();
		var contents=$("#contents").val();
		var passwd=$("#passwd").val();
		// 빈값이면 해당 태그로 마우스 포커스를 이동시키고 알람창을 띄운다.
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
		// 악성 첨부파일을 방지하기 위한 처리.
		var filename=document.form1.file1.value;
		// aaa.bbb.ccc.test.exe
		var start=filename.lastIndexOf(".")+1; // 마지막 마침표의 다음 위치의 인덱스를 저장한다.
		if(start != -1){ // 마침표가 있으면
			//확장자
			var ext=filename.substring(start, filename.length); // 저장한 인덱스에서 해당 파일이름의 크기만큼.
			if(ext == "jsp" || ext == "exe"){ // 즉 마침표 다음의 내용이 jsp이거나 exe이면 업로드하지 않는다.
				alert("업로드할 수 없는 파일입니다.");
				return;
			}
		}
		document.form1.submit(); // 모든 처리가 완료되면 form의 method로 넘겨준다.
	});
});
</script>
</head>
<body>
<c:if test="${param.message == 'error'}">
	<script>
		alert("업로드할 수 없는 파일입니다.");
	</script>
</c:if>
<h2>글쓰기</h2>
<form name="form1" method="post" action="/munch/board_servlet/insert.do" enctype="multipart/form-data">
<table border="1" width="700px">
	<tr>
		<td align="center">이름</td>
		<td><input name="writer" id="writer"></td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td><input name="subject" id="subject" size="60"></td>
	</tr>
	<tr>
		<td align="center">본문</td>
		<td><textarea rows="5" cols="60" name="contents" id="contents"></textarea></td>
	</tr>
	<tr>
		<td align="center">첨부파일</td>
		<td><input type="file" name="file1"></td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td><input type="password" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="확인" id="btnSave">
		</td>
	</tr>
</table>
</form>
</body>
</html> 