/*
- id가 #result인 div에 아래 페이지를 넣어준다.
    <script>
        $(function(){// 실행시 바로 시작
           list('1'); // 첫 페이지를 1로 설정
        });
        function list(cur_page){
            var param={"cur_page":cur_page}; // 원하는 페이지 번호
            $.ajax({
                url: "/page_servlet/list.do", // 요청 url
                data: param, // 페이지 번호를 넘겨준다.
                success: function(txt){ // 성공시 해당 id 부분에 출력해준다.
                    $("#result").html(txt);
                }
            })
        }
    </script>
*/