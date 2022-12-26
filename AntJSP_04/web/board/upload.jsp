<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String upload_path="c:/upload"; //업로드 디렉토리
int size=10*1024*1024; //제한 용량
String name="";
String subject="";
String filename1="", filename2="";
int filesize1=0, filesize2=0;
try{
	//HttpServletRequest => MultipartRequest
	MultipartRequest multi=new MultipartRequest(request, 
			upload_path, size, "utf-8", 
			new DefaultFileRenamePolicy()); //파일 이름 중복
	name=multi.getParameter("name");
	subject=multi.getParameter("subject");
	Enumeration files=multi.getFileNames(); //첨부파일 이름
	String file1=(String)files.nextElement();
	String file2=(String)files.nextElement();
	filename1=multi.getFilesystemName(file1);
	File f1=multi.getFile(file1); //파일 정보 참조
	filesize1=(int)f1.length(); //파일 사이즈
	filename2=multi.getFilesystemName(file2);
	File f2=multi.getFile(file2);
	filesize2=(int)f2.length();
}catch(Exception e){
	e.printStackTrace();
}
%>
이름: <%=name%><br>
제목: <%=subject%><br>
파일1 이름: <%=filename1%><br>
파일1 크기: <%=filesize1%><br>
파일2 이름: <%=filename2%><br>
파일2 크기: <%=filesize2%><br>
</body>
</html>