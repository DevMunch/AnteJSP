package board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Constants;
import page.PageUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();    // 요청한 URL      ex) /list.do
        String contextPath = request.getContextPath();      // 컨텍스트 패스      ex) /munch
        BoardDAO dao = new BoardDAO();

        // 페이징 처리 후 게시판 리스트 페이지로 이동
        if(url.indexOf("list.do")!=-1) {    // 게시글 리스트
            int count=dao.count(); //전체 레코드 개수
            int cur_page=1; //페이지 번호
            if(request.getParameter("cur_page")!=null) {
                cur_page=Integer.parseInt(request.getParameter("cur_page"));
            }
            PageUtil page=new PageUtil(count, cur_page);
            int start=page.getPageBegin(); //페이지 시작번호
            int end=page.getPageEnd(); //페이지 끝번호
            List<BoardDTO> list=dao.list(start, end); //페이지에 해당되는 리스트
            request.setAttribute("list", list); //출력 전에 저장
            request.setAttribute("page", page);
            RequestDispatcher rd=request.getRequestDispatcher("/board/list.jsp");
            rd.forward(request, response); //출력 페이지로 이동
        }
        // 게시물 추가
        else if(url.indexOf("insert.do")!=-1){ // 게시글 추가
            BoardDTO dto = new BoardDTO();
            File dir = new File(Constants.UPLOAD_PATH); // 파일업로드 디렉터리를 생성자로 하는 파일 객체 생성
            if(!dir.exists()){  // 만일 디렉터리가 없다면 생성.
                dir.mkdir();
            }
            // 파일 업로드를 위해서 cos.jar에 있는 MultipartRequest 객체 생성
            // 리쿼스트, 업로드 디렉터리, 최대 사이즈, 파일의 인코딩 형식, 중복된 파일이 넘어왔을때 처리방법(filename1,filename2,filename3)
            MultipartRequest multi = new MultipartRequest(request,Constants.UPLOAD_PATH,Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
            String filename = "-"; // 파일 이름의 기본값
            int filesize = 0;   // 파일의 사이즈의 기본값
            try{
                Enumeration<String> files = multi.getFileNames(); // 파일 목록을 가져온다.(파일이 여러개가 올 수 있으니)
                while(files.hasMoreElements()){ // 다음 요소가 있다면
                    String file1 = (String)files.nextElement(); // 다음 요소를 읽어서
                    filename = multi.getFilesystemName(file1);  // 해당 파일의 이름을 가져온다.
                    File f1 = multi.getFile(file1); // 파일의 사이즈를 알기 위해서 파일 객체에 file1을 가져와 저장한다.
                    if(f1!=null){ // f1이 가리키는 파일이 있다면
                        filesize = (int) f1.length(); // 해당 파일의 사이즈를 저장한다.
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            String writer = multi.getParameter("writer");
            String subject = multi.getParameter("subject");
            String contents = multi.getParameter("contents");
            String passwd = multi.getParameter("passwd");
            String ip = request.getRemoteAddr();   // 클라이언트 ip 주소
            dto.setWriter(writer);
            dto.setSubject(subject);
            dto.setContents(contents);
            dto.setPasswd(passwd);
            dto.setIp(ip);
            // 파일 이름이 null이거나 파일 이름의 좌우 공백을 제거했을 때 빈값이라면 " test.jpg " => "test.jpg"
            if(filename==null||filename.trim().equals("")){
                filename="-"; // 기본값으로 저장.
            }
            dto.setFilename(filename);
            dto.setFilesize(filesize);
            dao.insert(dto);    // 레코드 저장
            // 목록으로 이동.
            response.sendRedirect(contextPath+"/board_servlet/list.do");
        }
        // 조회수 증가 및 상세보기 페이지로 이동
        else if(url.indexOf("view.do")!=-1){
            int num = Integer.parseInt(request.getParameter("num")); // 글번호를 가져온다.
            HttpSession session = request.getSession(); // 세션 객체 생성
            dao.plus_hit(num, session); // 조회수 증가 처리(일정 시간 후에 조회수 증가시키기 위해서 세션을 활용)
            BoardDTO dto = dao.view(num); // 번호에 맞는 레코드를 가져온다.

            // 상세보기 페이지로 해당 레코드를 담아서 포워드해준다.
            request.setAttribute("dto",dto); // 가져온 레코드를 저장
            RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
            rd.forward(request,response);
        }
        // 첨부파일 다운로드 클릭시 처리
        else if(url.indexOf("download.do")!=-1){
            // 업로드 => 파일 정보 => 테이블  1. 테이블을 조회해서 파일 이름을 가져온다.
            //         파일 내용 => 디스크
            int num = Integer.parseInt(request.getParameter("num")); // 글번호
            String filename = dao.getFilename(num); // 다운로드할 파일이름 DB에서 가져온다. 아직 경로는 모른다.
            String path = Constants.UPLOAD_PATH + filename; // 경로를 가져온다.c:/upload/파일이름
            byte buffer[] = new byte[4096]; // 바이트 배열
            // 서버에서 읽어서 클라이언트로 보낸다.
            FileInputStream fis = new FileInputStream(path); // 서버에서 읽어들이므로 InputStream
            // 마임타입-파일의 종류를 알아와야한다. (텍스트인지 이미지인지등등...)
            String mimeType = getServletContext().getMimeType(path); // 파일의 종류를 가져온다.
            if(mimeType==null){ // 혹시나 종류를 잘 판단하지 못해서 null이 넘어올 경우
                mimeType = "application/octet-stream;charset=UTF-8"; // 범용적인 mimeType으로 설정한다.
            }
            // 보내는 형식 header(파일이름) + body(파일내용)
            // header는 url이므로 파일이름을 바꿔주어야한다. 한글등을 인식하지 못한다.
            // getBytes() 스트링을 바이트 배열로 풀어낸다. 즉 utf-8로된 것을 풀어내 인식할 수 있는 서유럽형식인 8859_1로 변경한다.
            filename = new String(filename.getBytes("utf-8"),"8859_1");
            // header 셋팅 구성 ( 첨부파일 이므로 Content-Disposition, 첨부파일이란 뜻의 attachment, 파일이름 filename)
            response.setHeader("Content-Disposition","attachment;filename");
            // body(파일 내용)
            // 클라이언트로 보내주어야 하므로 OutputStream 출력 스트림
            ServletOutputStream out = response.getOutputStream();
            int len;
            while(true){
                // 파일을 만들어둔 버퍼를 이용해서 읽는다.
                // 네트워크 상황에 맞춰서 0 ~ 최대 buffer 사이즈만큼 읽는다.
                len = fis.read(buffer,0,buffer.length);
                if(len==-1){ // 더이상 내용이 없다면
                    break; // 종료
                }
                out.write(buffer,0,len); // 클라이언트에 전송한다.
                // 즉 한번에 보내는게 아니라 정해둔 버퍼사이즈만큼 나누어서 더 이상 보낼게 없을때까지 나누어 보낸다.
            }
            out.flush(); // 출력 버퍼를 비운다.
            out.close(); // 출력 스트림 닫기
            fis.close(); // 입력 스트림 닫기
            dao.plus_down(num); // 다운로드 횟수를 증가 처리한다.
        }
        // 댓글을 추가
        else if(url.indexOf("insert_comment.do")!=-1){
            BoardCommentDTO dto = new BoardCommentDTO();
            int board_num = Integer.parseInt(request.getParameter("board_num")); // 글번호
            String writer = request.getParameter("writer"); // 댓글 작성자
            String contents = request.getParameter("contents"); // 댓글 내용
            dto.setBoard_num(board_num);    // 글번호 저장
            dto.setWriter(writer);          // 댓글 작성자 저장
            dto.setContents(contents);      // 댓글 내용 저장
            // 댓글 번호는 자동으로 저장중이므로 따로 보내지않는다.
            dao.insert_comment(dto); // 디비에 저장.
            // 이후 아무런 처리를하지 않지만 ajax 콜백함수로 다시 호출된다.
        }
        // 댓글의 목록(리스트) 출력
        else if(url.indexOf("list_comment.do")!=-1){
            int num = Integer.parseInt(request.getParameter("num")); // 글 번호
            List<BoardCommentDTO> list = dao.list_comment(num); // 댓글 리스트를 가져온다.
            request.setAttribute("list",list); // 댓글 리스트를 저장
            String page = "/board/list_comment.jsp"; // 해당 페이지로 이동(단, ajax호출이므로 해당 게시글 아래 지정된 곳에 출력)
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request,response);
        }
        // 수정/삭제와 같은 작업을 할때 비밀번호가 맞는지 체크하기 위한 메서드
        else if(url.indexOf("check_pwd.do")!=-1){
            int num = Integer.parseInt(request.getParameter("num")); // 글번호
            String passwd = request.getParameter("passwd"); // 사용자가 입력한 비밀번호
            String result = dao.check_pwd(num,passwd);  // 입력한 비밀번호가 실제 비밀번호와 일치하는지 체크
            String page = "";
            if(result!=null){   // 비밀번호가 일치할 시
                page = "/board/edit.jsp";   // 수정 페이지
                request.setAttribute("dto",dao.view(num));  // 해당 글번호의 데이터를 담는다.
                RequestDispatcher rd =request.getRequestDispatcher(page);
                rd.forward(request,response);
            }else{  // 비밀번호가 일치하지 않을 시
                page = contextPath + "/board_servlet/view.do?num="+num+"&message=error";
                response.sendRedirect(page);
            }
        }
        // 수정시 수행되는 메서드
        else if(url.indexOf("update.do")!=-1) {
            BoardDTO dto = new BoardDTO();
            // 파일 업로드를 위해서 cos.jar에 있는 MultipartRequest 객체 생성
            // 리쿼스트, 업로드 디렉터리, 최대 사이즈, 파일의 인코딩 형식, 중복된 파일이 넘어왔을때 처리방법(filename1,filename2,filename3)
            MultipartRequest multi = new MultipartRequest(request,Constants.UPLOAD_PATH,Constants.MAX_UPLOAD,
                    "utf-8", new DefaultFileRenamePolicy());
            String filename = "-"; // 파일 이름의 기본값
            int filesize = 0;   // 파일의 사이즈의 기본값
            try{
                Enumeration<String> files = multi.getFileNames(); // 파일 목록을 가져온다.(파일이 여러개가 올 수 있으니)
                while(files.hasMoreElements()){ // 다음 요소가 있다면
                    String file1 = (String)files.nextElement(); // 다음 요소를 읽어서
                    filename = multi.getFilesystemName(file1);  // 해당 파일의 이름을 가져온다.
                    File f1 = multi.getFile(file1); // 파일의 사이즈를 알기 위해서 파일 객체에 file1을 가져와 저장한다.
                    if(f1!=null){ // f1이 가리키는 파일이 있다면
                        filesize = (int) f1.length(); // 해당 파일의 사이즈를 저장한다.
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            String writer = multi.getParameter("writer");
            String subject = multi.getParameter("subject");
            String contents = multi.getParameter("contents");
            String passwd = multi.getParameter("passwd");
            String ip = multi.getParameter("ip");   // 클라이언트 ip 주소
            int num=Integer.parseInt(multi.getParameter("num")); // 글번호
            String delete_file=multi.getParameter("delete_file"); // 첨부파일 삭제 체크박스를 체크했는지 값을 가져온다.
            // 체크박스의 값이 null이 아니고 체크를하였을시 넘어오는 string 값인 on이라면 수행한다. 즉 체크시 on 아니면 null
            if(delete_file!=null&&delete_file.equals("on")){ // 체크시
                String fileName=dao.getFilename(num); // 파일 이름
                File f = new File(Constants.UPLOAD_PATH+fileName); // 해당 첨부파일 객체를 생성
                f.delete(); // 첨부파일 삭제처리.
                dto.setWriter(writer);
                dto.setSubject(subject);
                dto.setContents(contents);
                dto.setPasswd(passwd);
                dto.setIp(ip);
                // 파일을 삭제하였으므로 파일관련 필드들을 초기화 해준다.
                dto.setFilename("-");   // 파일명 기본값 "-"
                dto.setFilesize(0); // 파일 사이즈 0
                dto.setDown(0); // 파일 다운로드 횟수 0
                dao.update(dto);
            }
            dto.setNum(num); // 글번호, 가장중요
            dto.setWriter(writer);
            dto.setSubject(subject);
            dto.setContents(contents);
            dto.setPasswd(passwd);
            dto.setIp(ip);
            // 파일 이름이 null이거나 파일 이름의 좌우 공백을 제거했을 때 빈값이라면 " test.jpg " => "test.jpg"
            if(filename==null||filename.trim().equals("")){ // 새로운 첨부파일이 없다면
                BoardDTO dto2 = dao.view(num);  // 기존 첨부파일 정보 확인을 위해서 그대로 정보를 가져온다.
                String name=dto2.getFilename();
                int size=dto2.getFilesize();
                int down=dto2.getDown();
                // 현재 dto에 dto2를 이용해서 가져온 기존 파일정보만 입력한다.
                dto.setFilename(name);
                dto.setFilesize(size);
                dto.setDown(down);
            }else{ // 새로운 첨부파일이 있다면.
                dto.setFilename(filename);
                dto.setFilesize(filesize);
            }
            String result=dao.check_pwd(num,passwd); // 비밀번호 체크
            if(result!=null) {  // 비밀번호가 맞다면
                dao.update(dto); // 수정
                String page = contextPath + "/board_servlet/list.do";
                response.sendRedirect(page); // 목록으로 이동
            }else{ // 비밀번호가 틀렸다면
                request.setAttribute("dto",dto);
                String page="/board/edit.jsp?pwd_error=y"; // 에러메시지 출력
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request,response);
            }
        }
        // 삭제시 수행되는 메서드
        else if(url.indexOf("delete.do")!=-1) {
            // form에 enctype="multipart/form-data"가 들어가면 기존 request로는 처리가 불가능하다.
            // 그러므로 파일첨부를 하지는 않지만 MultipartRequest객체를 생성한다.
            MultipartRequest multi=new MultipartRequest(request,
                    Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8",
                    new DefaultFileRenamePolicy());
            //글번호
            int num=Integer.parseInt(multi.getParameter("num")); // 글번호
            String passwd=multi.getParameter("passwd"); //비밀번호
            String result=dao.check_pwd(num, passwd); //비밀번호 확인
            if(result!=null) { //맞으면
                dao.delete(num); //삭제
                String page=contextPath+"/board_servlet/list.do"; //리스트
                response.sendRedirect(page); //이동
            }else { //틀리면
                request.setAttribute("dto", dao.view(num)); // 해당 게시물 정보를 다시 저장해서
                String page="/board/edit.jsp?pwd_error=y"; // 에러메시지 출력
                RequestDispatcher rd=request.getRequestDispatcher(page);
                rd.forward(request, response); // 다시 에러메시지와 함께 수정 페이지로
            }
        }
        // 답변글을 작성하는 페이지로 이동
        else if(url.indexOf("input_reply.do")!=-1) {
            // 부모 게시글의 글번호
            int num=Integer.parseInt(request.getParameter("num"));
            // 부모 게시글의 내용을 가져오기 위해서 부모의 정보를 가져온다.
            BoardDTO dto=dao.view(num);
            dto.setContents("====contents===\n"+dto.getContents());
            request.setAttribute("dto", dto); // 부모의 정보를 가지고 답변글 작성 페이지로 이동한다.
            String page="/board/reply.jsp";
            RequestDispatcher rd=request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
        /*
            num : 글번호 / group_num : 그룹번호 / re_order : 답변글 그룹의 순서 / re_depth : 글 깊이, 최상위 부모 0

                        num group_num re_order re_depth
            B            5      5        1         0
              Re:B       6      5        2         1
            A            1      1        1         0
              Re:A       4      1        2         1
                    // 만일 이곳에 새 답변이 달리면 re_order의 순서를 조정해주어야 한다.
                    // 즉 여기에 re_order 3번이 들어간다면 3이상의 re_order들을 + 1 처리해야 한다.
                Re:A     9      1        3         2
                  Re:A   7      1        4         2
                  Re:A   8      1        5         3
                Re:A     2      1        6         1
                  Re:A   3      1        7         2
        */
        //  답변글을 저장하는 메서드
        else if(url.indexOf("insert_reply.do")!=-1) {
            int num=0;
            if(request.getParameter("num")!=null) { // 부모글의 번호가 넘어왔다면
                num=Integer.parseInt(request.getParameter("num")); // 부모글 번호를 num에 저장
            }
            BoardDTO dto=dao.view(num); // 부모 게시글의 정보
            int group_num=dto.getGroup_num(); // 답변글 그룹번호, 그룹번호는 부모와 자식이 모두 같아야하므로 동일하게한다.
            int re_order=dto.getRe_order()+1; // 답변글의 순번
            int re_depth=dto.getRe_depth()+1; // 답변 단계
            // 답변 게시글에 작성된 정보들을 가져온다.
            String writer=request.getParameter("writer");
            String subject=request.getParameter("subject");
            String contents=request.getParameter("contents");
            String passwd=request.getParameter("passwd");
            // dto에 묶어서 저장
            dto.setWriter(writer);
            dto.setSubject(subject);
            dto.setContents(contents);
            dto.setPasswd(passwd);
            dto.setGroup_num(group_num);
            dto.setRe_order(re_order);
            dto.setRe_depth(re_depth);
            dto.setFilename("-");
            dto.setFilesize(0);
            dto.setDown(0);
            dao.update_order(group_num, re_order); // 답변글의 순번 조정, 즉 새글이 들어올 경우에 re_order의 순서 조정이 필요하다.
            dao.insert_reply(dto); //답변글 저장
            String page="/board_servlet/list.do";
            response.sendRedirect(request.getContextPath()+page);
        }
        //  검색을 처리하는 메서드
        else if(url.indexOf("search.do")!=-1) {
            String search_option=request.getParameter("search_option"); // 검색 옵션
            String keyword=request.getParameter("keyword"); // 검색 키워드
            int count=dao.count(search_option,keyword); // 검색된 레코드의 개수
            int cur_page=1;
            if(request.getParameter("cur_page")!=null) {
                cur_page=Integer.parseInt(request.getParameter("cur_page"));
            }
            // 검색된 레코드가 많을 수도 있으니 페이징 처리를 한다.
            PageUtil page=new PageUtil(count, cur_page);
            int start=page.getPageBegin();
            int end=page.getPageEnd();
            List<BoardDTO> list=dao.list_search(search_option, keyword, start, end);
            request.setAttribute("list", list);
            request.setAttribute("search_option", search_option);
            request.setAttribute("keyword", keyword);
            request.setAttribute("page", page);
            // 사실상 search.jsp는 필요없다. 그냥 list.jsp로 보내면 되지만 그냥 학습에 헷가릴까봐 추가했다.
            // search.jsp와 list.jsp는 동일하다.
            RequestDispatcher rd=request.getRequestDispatcher("/board/search.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
