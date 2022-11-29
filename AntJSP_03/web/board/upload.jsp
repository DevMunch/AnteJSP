<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String upload_path="c:/upload";
    int size=10*1024*1024;
    String name="";
    String subject="";
    String filename1="", filename2="";
    int filesize1=0, filesize2=0;
    try{
        // DefaultFileRenamePolicy() 파일이름이 중복되면 덮어쓰여지므로 이속성을 통해 숫자를 붙여준다. ex) a, a1, a2
        MultipartRequest multi=new MultipartRequest(request, upload_path, size, "utf-8", new DefaultFileRenamePolicy());
        name=multi.getParameter("name");
        subject=multi.getParameter("subject");
        Enumeration files=multi.getFileNames(); // 첨부파일 이름
        String file1=(String)files.nextElement();
        String file2=(String)files.nextElement();
        filename1=multi.getFilesystemName(file1);
        File f1 = multi.getFile(file1); // 파일 정보를 참조하는 클래스
        filesize1=(int)f1.length();
        filename2=multi.getFilesystemName(file2);
        File f2 = multi.getFile(file2);
        filesize2 = (int)f2.length();
    }catch (Exception e){
        e.printStackTrace();
    }
%>
</body>
</html>