<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(function(){
      $("#btnUpdate").click(function(){
        var writer = $("#writer");
        var memo = $("#memo");

        if (writer.val() == "") {
          alert("이름을 입력하세요"); writer.focus();
          return;
        }
        if (memo.val() == "") {
          alert("메모를 입력하세요"); memo.focus();
          return;
        }
        document.form1.action="/memo_servlet/update.do";

        document.form1.submit();
      });
      $("#btnDelete").click(function(){
        if (confirm("삭제하시겠습니까?")) {
          document.form1.action="/memo_servlet/del.do";
          document.form1.submit();
        }
      });
    });
  </script>
</head>
<body>
<h2>메모 수정</h2>
<form name="form1" method="post"> <table border="1" style="width: 550px">
  <tr>
    <td>이름</td>
    <td><input type="text" name="writer" id="writer" value="
${map.WRITER}"></td>
  </tr>
  <tr>
    <td>메모</td>
    <td><input type="text" name="memo" id="memo" size="60"
               value="${map.MEMO}"></td>
  </tr>
  <tr align="center">
    <td colspan="2">
      <input type="hidden" name="idx" value="${map.IDX}"> <input type="button" value="수정" id="btnUpdate">
      <input type="button" value="삭제" id="btnDelete"> </td>
  </tr>
</table>
</form>
</body>
</html>
