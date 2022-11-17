<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function(){ // 자동 샐행
            $("#button1").click(function(){
                var num =$("#num").val(); // 태그 입력값.
                // 백그라운드로 실행
                $.ajax({
                    type: "post",  // post 방식으로 넘긴다.
                    url: "result1.jsp", // 백그라운드로 호출할 페이지
                    data: {"num":num}, // 넘길 데이터
                    success: function(txt){ // 콜백함수, 백그라운드 완료후 여기서 실행, response text
                        $("#result").html(txt);
                    }
                });
            });
        });
    </script>
</head>
<body>
<h2>동기식</h2>
<form action="result1.jsp">
number: <input type="text" name="num">
<input type="submit" value="확인">
</form>

<h2>비동기식</h2>
number:
<input type="text" id="num">
<input type="button" id="button1" value="확인">
<div id="result">결과 출력 영역</div>
</body>
</html>
