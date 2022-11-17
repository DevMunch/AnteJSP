<%--
  Created by IntelliJ IDEA.
  User: songimyeong
  Date: 2022/11/17
  Time: 3:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
      $(function(){
        $("#btnLogin").click(function (){
          var id=$("#id").val();
          var pwd=$("#pwd").val();
          var params={"id":id,"pwd":pwd};
          $.ajax({
            type:"post",
            url:"result2.jsp",
            data:params,
            success:function(txt){
              $("#div1").html(txt);
            }
          })
        });
      });
    </script>
</head>
<body>
아이디 : <input id="id"><br>
비번 : <input type="password" id="pwd"><br> <input type="button" value="확인" id="btnLogin">
<div id="div1">결과 출력 영역</div>
</body>
</html>
