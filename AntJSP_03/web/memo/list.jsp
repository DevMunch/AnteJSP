<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(function() {
      $("#chkAll").click(function() {
        if ($("#chkAll").prop("checked")) {
          $("input[name=idx]").prop("checked", true);
        } else {
          $("input[name=idx]").prop("checked", false);
        }
      });
      $("#btnAllDel").click(function()
      {
        document.form1.action="/memo_servlet/delete_all.do";
        document.form1.submit();
      });
    });

    function memo_del(idx) {
    location.href = "/memo_servlet/del.do?idx=" + idx;
  }
  </script>
</head>
<body>
<form method="post" name="form1">
  <table border="1">
    <tr>
      <th><input type="checkbox" id="chkAll"></th> <th>번호</th>
      <th>이름</th>
      <th>메모</th>
      <th>날짜</th>
      <th><input type="button" value="선택삭제" id="btnAllDel"></th>
    </tr>
    <c:forEach var="row" items="${list}" varStatus="s">
      <tr>
        <td><input type="checkbox" name="idx" value="${row.IDX}"></td>
        <td>${row.IDX}</td>
        <td>${row.WRITER}</td>
        <td><a href="/memo_servlet/view.do?idx=${row.IDX}">${row.MEMO}</a></td>
        <td>${row.POST_DATE}</td>
        <td><input type="button" value="삭제" onclick="memo_del('${row.IDX}')"></td>
      </tr>
    </c:forEach>
  </table>
</form>
</body>
</html>
