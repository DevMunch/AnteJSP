package guestbook;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/gb_servlet/*")
public class GuestbookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();   // 요청한 주소
        GuestbookDAO dao = new GuestbookDAO();
        if(uri.indexOf("list.do")!=-1){
            String searchkey="name";    // 검색옵션
            String search="";   // 검색키워드
            if(request.getParameter("searchkey")!=null){
                searchkey=request.getParameter("searchkey");
            }
            if(request.getParameter("search")!=null){
                search=request.getParameter("search");
            }
            List<GuestbookDTO> items=dao.list(searchkey,search);
            request.setAttribute("list",items);
            request.setAttribute("count",items.size()); // 레코드 개수
            request.setAttribute("searchkey",searchkey);
            request.setAttribute("search",search);
            String url="/guestbook/list.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
        }else if(uri.indexOf("insert.do")!=-1){
            // 입력한 내용들
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String passwd=request.getParameter("passwd");
            String contents=request.getParameter("contents");
            GuestbookDTO dto = new GuestbookDTO();
            dto.setName(name);
            dto.setEmail(email);
            dto.setPasswd(passwd);
            dto.setContents(contents);
            dao.insert(dto);    // 레코드 저장
            String url="/gb_servlet/list.do";
            response.sendRedirect(request.getContextPath()+url);
        }else if(uri.indexOf("passwd_check.do")!=-1){
            // 글번호
            int idx=Integer.parseInt(request.getParameter("idx"));
            // 비번
            String passwd=request.getParameter("passwd");
            // 비번이 맞으면 true, 틀리면 false
            Boolean result=dao.check_pwd(idx, passwd);
            String url="";
            if(result){ // 비번이 맞으면 편집 페이지
                url="/guestbook/edit.jsp";
                GuestbookDTO dto = dao.detail(idx);
                request.setAttribute("dto",dto);
            }else{ // 비번이 틀리면 리스트 페이지
                url="/gb_servlet/list.do";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
        }else if(uri.indexOf("update.do")!=-1){
            // 수정할 글번호
            int idx=Integer.parseInt(request.getParameter("idx"));
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String passwd=request.getParameter("passwd");
            String contents=request.getParameter("contents");
            GuestbookDTO dto = new GuestbookDTO();
            dto.setIdx(idx);
            dto.setName(name);
            dto.setEmail(email);
            dto.setPasswd(passwd);
            dto.setContents(contents);
            dao.update(dto);
            String url="/gb_servlet/list.do";
            response.sendRedirect(url);
        }else if(uri.indexOf("delete.do")!=-1){
            // 삭제할 글번호
            int idx=Integer.parseInt(request.getParameter("idx"));
            dao.delete(idx);
            String url="/gb_servlet/list.do";
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
