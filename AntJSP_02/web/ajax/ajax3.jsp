<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/17
  Time: 4:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(function(){
      $("#search").keyup(function(){ // key 이벤트
        var search=$("#search").val(); // 텍스트값
        if(search.length==0){ // 글자수가 0이면 숨긴다.
            $("#div1").css("visibility","hidden");
        }else{  // 글자수가 0보다 크면 표시한다.
            $("#div1").css("visibility","visible");
            $.ajax({
               url: "result3.jsp",
               data:{"search":search},
               success: function(txt){
                   $("#div1").html(txt);
               }
            });
        }
      });
    });
  </script>
</head>
<body>
키워드를 입력하세요
<input type="text" id="search">
<div id="div1"></div>
</body>
</html>
