<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
      $(function(){ // 자동실행, 리스트 함수에 페이지 번호로 1을 보낸다
        list("1");
      });
      function list(cur_page){
        var param={"cur_page": cur_page}; // 페이지 번호
        $.ajax({
          url:"/page_servlet/list.do",
          data:param,
          success:function (txt){
            $("#result").html(txt);
          }
        });
      }
    </script>
</head>
<body>
<h2>페이지 나누기</h2>
<div id="result"></div>
</body>
</html>
