package shop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login_servlet/*")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url=request.getRequestURI();     // 요청한 주소
        String path=request.getContextPath();
        MemberDAO dao = new MemberDAO();
        if(url.indexOf("login.do")!=-1){
            String userid=request.getParameter("userid");
            String passwd=request.getParameter("passwd");
            MemberDTO dto = new MemberDTO();
            dto.setUserid(userid);
            dto.setPasswd(passwd);
            String name = dao.login(dto);
            if(name==null){ // 로그인 실패
                String page="/shop/login.jsp?message=error";
                response.sendRedirect(path+page);
            }else{  // 로그인 성공
                HttpSession session = request.getSession();
                session.setAttribute("userid", userid);
                session.setAttribute("name",name);
                request.setAttribute("result",name+"님 환영합니다.");
                String page="/shop/login_result.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request,response);
            }
        }else if(url.indexOf("logout.do")!=-1){
            HttpSession session = request.getSession();
            session.invalidate();   // 세션 초기화
            String page=path+"/shop/login.jsp?message=logout";
            response.sendRedirect(page);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
