package SecurityMember;

import member.MemberDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/encrypt_servlet/*")
public class EncryptController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String context = request.getContextPath(); // 프로젝트 id
        MemberEncryptDAO dao = new MemberEncryptDAO();
        if(url.indexOf("join_encrypt.do")!=-1){
            String userid=request.getParameter("userid");
            String passwd=request.getParameter("passwd");
            String name=request.getParameter("name");
            MemberEncryptDTO dto = new MemberEncryptDTO();
            dto.setUserid(userid);
            dto.setPasswd(passwd);
            dto.setName(name);
            dao.insertEncript(dto);
            response.sendRedirect(context+"/securityMember/login_encrypt.jsp");
        }else if(url.indexOf("login.do")!=-1){
            String userid = request.getParameter("userid");
            String passwd = request.getParameter("passwd");
            MemberEncryptDTO dto = new MemberEncryptDTO();
            dto.setUserid(userid);
            dto.setPasswd(passwd);
            String result = dao.login(dto); request.setAttribute("result", result);
            String page = "/securityMember/login_result.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
