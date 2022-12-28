<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function delete_product(){
            if(confirm("삭제하시겠습니까?")){
                document.form1.action="/munch/admin_servlet/delete.do";
                document.form1.submit();
            }
        }
        function update_product(){
            //document : html 최상위객체
            var product_name=document.form1.product_name.value;
            var price=document.form1.price.value;
            var description=document.form1.description.value;
            if(product_name==""){
                alert("상품명을 입력하세요.");
                document.form1.product_name.focus();
                return;
            }
            if(price==""){
                alert("가격을 입력하세요.");
                document.form1.price.focus();
                return;
            }
            if(description==""){
                alert("상품설명을 입력하세요.");
                document.form1.description.focus();
                return;
            }
            document.form1.action="/munch/admin_servlet/update.do";
            document.form1.submit();
        }
    </script>
</head>
<body>
<%@include file="../include/admin_menu.jsp"%>
<h2>상품수정</h2>
<form name="form1" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>상품명</td>
            <td><input name="product_name" value="${dto.product_name}"></td>
        </tr>
        <tr>
            <td>가격</td>
            <td><input name="price" value="${dto.price}"></td>
        </tr>
        <tr>
            <td>상품설명</td>
            <td><textarea rows="5" cols="60" name="description">${dto.description}</textarea></td>
        </tr>
        <tr>
            <td>상품이미지</td>
            <td>
                <img src="/munch/images/${dto.filename}" width="300px" height="300px">
                <input type="file" name="file1">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <!--수정 삭제에 필욯나 상품코드-->
                <input type="hidden" name="product_code" value="${dto.product_code}">
                <input type="button" value="수정" onclick="update_product()">
                <input type="button" value="삭제" onclick="delete_product()">
                <input type="button" value="목록" onclick="location.href='/munch/product_servlet/list.do'">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
