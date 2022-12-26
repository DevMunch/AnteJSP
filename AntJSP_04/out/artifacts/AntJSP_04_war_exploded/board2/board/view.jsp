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
$(function(){ //자동 실행
	$("#btnEdit").click(function(){ //수정/삭제 버튼
		document.form1.action="/munch/board_servlet/check_pwd.do"; // 비밀번호 체크
		document.form1.submit();
	});
	$("#btnList").click(function(){ //목록 버튼
		location.href="/munch/board_servlet/list.do";
	});
	list_comment(); //댓글 리스트
	$("#btnSave").click(function(){ //댓글 저장 버튼
		insert_comment(); // 댓글 저장 함수
	});
	$("#btnReply").click(function(){ //답변 버튼
		document.form1.action="/munch/board_servlet/input_reply.do"; // 답변 쓰기로 이동
		document.form1.submit();
	});
});
function insert_comment(){
	// 게시글 번호, 댓글 작성자, 댓글 내용을 저장해서 전달한다.
	var params={"board_num":${dto.num}, "writer":$("#writer").val(), "contents":$("#contents").val()};
	$.ajax({
		type:"post",
		url:"/munch/board_servlet/insert_comment.do",
		data: params, // 위의 3개의 값을 전달.
		success:function(){ //콜백함수(댓글 저장이 완료된 후 호출)
			$("#writer").val(""); // 댓글 작성자 태그의 입력값을 초기화
			$("#contents").val(""); // 댓글 내용 태그의 입력값을 초기화
			list_comment(); //댓글 목록 갱신 
		}
	});
}
function list_comment(){
	$.ajax({
		url:"/munch/board_servlet/list_comment.do",
		data: {"num":${dto.num}}, // 글번호
		success:function(result){
			$("#div_comment").html(result); // 수행완료후 댓글 페이지를 해당 div태그에 출력
		}
	});
}
</script>
</head>
<body>
<h2>상세화면</h2>
<form name="form1" method="post">
<table border="1" width="700px">
	<tr>
		<td width="10%" align="center">날짜</td>
		<td width="40%">${dto.reg_date}</td>
		<td width="10%">조회수</td>
		<td width="40%">${dto.hit}</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td colspan="3">${dto.writer}</td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td colspan="3">${dto.subject}</td>
	</tr>
	<tr>
		<td align="center">본문</td>
		<td colspan="3">${dto.contents}</td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td colspan="3"><input type="password" name="passwd" id="passwd">
			<c:if test="${param.message == 'error' }">
				<span style="color:red">비밀번호가 일치하지 않습니다.</span>
			</c:if>
		</td>
	</tr>
	<tr>
		<td align="center">첨부파일</td>
		<td colspan="3">
			<!-- 파일 사이즈가 0보다 크다면 파일의 이름과 사이즈, 다운로드링크를 제공한다.-->
			<c:if test="${dto.filesize > 0 }">
				${dto.filename} ( ${dto.filesize} bytes )
				<a href="/munch/board_servlet/download.do?num=${dto.num}">[다운로드]</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<!-- 수정,삭제에 글번호가 필요하므로 hidden으로 처리한다. -->
			<input type="hidden" name="num" value="${dto.num}">
			<input type="button" value="수정/삭제" id="btnEdit">
			<input type="button" value="답변" id="btnReply">
			<input type="button" value="목록" id="btnList">
		</td>
	</tr>
</table>
</form>
<!-- 댓글 입력-->
<table border="0" width="700px">
	<tr>
	<!-- placeholder 입력도움말 -->
		<td><input id="writer" placeholder="이름"></td>
		<td rowspan="2"> <!-- 세로 셀 병합 -->
			<button id="btnSave" type="button">확인</button>
		</td>
	</tr>
	<tr>
		<td><textarea rows="5" cols="80" placeholder="내용" id="contents"></textarea></td>
	</tr>
</table>
<!-- 댓글 목록을 이곳에 출력 -->
<div id="div_comment"></div>
</body>
</html>