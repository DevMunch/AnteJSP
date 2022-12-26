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
	$("#btnWrite").click(function(){
		location.href="/munch/board/write.jsp";
	});
});
function list(page){
	location.href="/munch/board_servlet/list.do?cur_page="+page+"&search_option=${search_option}&keyword=${keyword}";
}
</script>
</head>
<body>
<h2>게시판</h2>
<form name="form1" method="post" 
action="/munch/board_servlet/search.do">
<select name="search_option"> <!-- 검색옵션 -->
<c:choose>
<!-- 전체검색의 경우 이름 제목 내용 모두에서 찾는다.-->
<c:when test="${search_option == null || search_option == 'all' }">
	<option value="all" selected>전체검색</option>
	<option value="writer">이름</option>
	<option value="subject">제목</option>
	<option value="contents">내용</option>
</c:when>
<!-- 이름의 경우 이름에서만 찾는다. -->
<c:when test="${search_option == 'writer' }">
	<option value="all">전체검색</option>
	<option value="writer" selected>이름</option>
	<option value="subject">제목</option>
	<option value="contents">내용</option>
</c:when>
<!-- 제목에서만 찾는다. -->
<c:when test="${search_option == 'subject' }">
	<option value="all">전체검색</option>
	<option value="writer">이름</option>
	<option value="subject" selected>제목</option>
	<option value="contents">내용</option>
</c:when>
<!-- 내용에서만 찾는다. -->
<c:when test="${search_option == 'contents' }">
	<option value="all">전체검색</option>
	<option value="writer">이름</option>
	<option value="subject">제목</option>
	<option value="contents" selected>내용</option>
</c:when>
</c:choose>
</select>
<input name="keyword" value="${keyword}"> <!-- 검색키워드 -->
<input type="submit" value="검색" id="btnSearch">
<button type="button" id="btnWrite">글쓰기</button>
</form>
<table border="1" width="900px">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>제목</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>첨부파일</th>
		<th>다운로드</th>
	</tr>
<c:forEach var="dto" items="${list}">
	<tr align="center">
		<td>${dto.num}</td>
		<td>${dto.writer}</td>
		<td align="left">
			<!-- 답글이라면 그 깊이만큼 공백을 준다. 원글은 깊이가 0 이므로 공백이 없다. -->
			<c:forEach var="i" begin="1" end="${dto.re_depth}">
				&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${dto.re_depth > 0 }">Re:</c:if> <!-- 답글이라면 -->
			<!--제목 클릭시 view.do로 이동-->
			<a href="/munch/board_servlet/view.do?num=${dto.num}">${dto.subject}</a>
			<c:if test="${dto.count_comments > 0 }"> <!-- 댓글이 있다면 -->
				<span style="color:green">( ${dto.count_comments} )</span>
			</c:if>
		</td>
		<td>${dto.reg_date}</td>
		<td>${dto.hit}</td>
		<td align="center">
			<c:if test="${dto.filesize > 0 }">
				<a href="/munch/board_servlet/download.do?num=${dto.num}">
					<img src="/munch/images/file_small.png" width="30px" height="30px">
				</a>
			</c:if>
		</td>
		<td>${dto.down}</td>
	</tr>
</c:forEach>
	<tr align="center">
		<td colspan="7">
			<c:if test="${page.curPage > 1 }">
				<a href="#" onclick="list('1')">[처음]</a>
			</c:if>
			<c:if test="${page.curBlock > 1 }">
				<a href="#" onclick="list('${page.prevPage}')">[이전]</a>
			</c:if>
			<c:forEach var="num" begin="${page.blockStart}" end="${page.blockEnd}">
				<c:choose>
					<c:when test="${num == page.curPage }">
						<span style="color:red">${num}</span> <!-- 현재 페이지 -->
					</c:when>
					<c:otherwise> <!-- 현재 페이지가 아니면 --> 
						<a href="#" onclick="list('${num}')">${num}</a>
					</c:otherwise>
				</c:choose>			
			</c:forEach>
			<c:if test="${page.curBlock < page.totBlock }">
				<a href="#" onclick="list('${page.nextPage}')">[다음]</a>
			</c:if>
			<c:if test="${page.curPage < page.totPage }">
				<a href="#" onclick="list('${page.totPage}')">[끝]</a>
			</c:if>			
		</td>
	</tr>	
</table>
</body>
</html>







