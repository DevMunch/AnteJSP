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
	$("#btnDelete").click(function(){//삭제 버튼 클릭
		if(confirm("삭제하시겠습니까?")){
			document.form1.action="/munch/board_servlet/delete.do";
			document.form1.submit();
		}
	});
	$("#btnUpdate").click(function(){ // 수정 버튼 클릭
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
		var filename=document.form1.file1.value;
		// aaa.bbb.ccc.test.exe
		var start=filename.lastIndexOf(".")+1;
		if(start != -1){ // 마침표가 있으면
			//확장자
			var ext=filename.substring(start, filename.length);
			if(ext == "jsp" || ext == "exe"){
				alert("업로드할 수 없는 파일입니다.");
				return;
			}
		}
		document.form1.action="/munch/board_servlet/update.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<h2>수정/삭제</h2>
<form name="form1" method="post" enctype="multipart/form-data">
<table border="1" width="700px">
	<tr>
		<td align="center">이름</td>
		<td><input name="writer" id="writer" value="${dto.writer}"></td>
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
		<td align="center">첨부파일</td>
		<td>
			<c:if test="${dto.filesize > 0 }"><!--첨부파일 사이즈가 0보다 크면 첨부파일이 있으므로-->
				${dto.filename} ( ${dto.filesize} bytes )<!--파일이름과 사이즈를 표시-->
				<input type="checkbox" name="delete_file">첨부파일 삭제<!--삭제 체크박스를 표시-->
				<br>
			</c:if>
			<input type="file" name="file1"><!--새로운 첨부파일을 추가하기 위한 file input태그-->
		</td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td>
			<input type="password" name="passwd" id="passwd">
			<c:if test="${param.pwd_error == 'y' }">
				<span style="color:red">비밀번호가 틀렸습니다.</span>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<!-- 수정,삭제에 필요한 글번호를 전달하기 위해서 hidden을 사용 -->
			<input type="hidden" name="num" value="${dto.num}">
			<input type="button" value="수정" id="btnUpdate">
			<c:if test="${dto.count_comments == '0' }">
				<input type="button" value="삭제" id="btnDelete">
			</c:if>			
		</td>
	</tr>
</table>
</form>
</body>
</html> 