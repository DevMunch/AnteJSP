<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function(){ // 자동 실행
           list(); // 리스트를 뽑아와서 출력
           $("#btnSave").click(function (){
               insert();
           });
           $("#btnSearch").click(function (){
               list();
           });
        });
        function list(){
            // 자바스크립트 객체 형식
            var params={"search_option":$("#search_option").val(),
            "search":$("#search").val()};
            $.ajax({
               data: params,
               url: "/memo_servlet/list.do",
               success: function (txt){
                   $("#result").html(txt);
               }
            });
        }
        function insert(){
            var writer=$("#writer").val();
            var memo=$("#memo").val();
            // 객체로 만들어서 전달.
            var params={"writer":writer,"memo":memo};
            $.ajax({
               type:"post",
               url:"/memo_servlet/insert.do",
               data:params,
                // 끝나면 다시 빈값으로 만들어준다.
               success:function(){
                   list("search=");
                   $("#writer").val("");
                   $("#memo").val("");
               }
            });
        }
    </script>
</head>
<body>
이름: <input id="writer" size="10"><br>
메모: <input id="memo" size="40">
<input type="button" id="btnSave" value="확인"><br>
<select id="search_option">
    <option value="writer">이름</option>
    <option value="memo">메모</option>
    <option value="writer_memo">이름+메모</option>
</select>
<input type="text" id="search" value="${search}">
<input type="button" id="btnSearch" value="조회">
<div id="result"></div>
</body>
</html>
