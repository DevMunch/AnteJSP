package shop;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin_servlet/*")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String path = request.getContextPath();
        if(url.indexOf("login.do")!=-1){
            AdminDAO dao = new AdminDAO();
            String userid = request.getParameter("userid");
            String passwd = request.getParameter("passwd");
            MemberDTO dto = new MemberDTO();
            dto.setUserid(userid);
            dto.setPasswd(passwd);
            String name = dao.login(dto);
            if(name==null){
                String page = path + "/shop/admin_login.do?message=error";
                response.sendRedirect(page);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("admin_userid", dto.getUserid());
                session.setAttribute("admin_name", name);
                session.setAttribute("userid", userid);
                session.setAttribute("name", name);
                session.setAttribute("result", name + "님 환영합니다.");
                String page = "shop/admin_result.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request, response);
            }
        }else if(url.indexOf("logout.do")!=-1){
            HttpSession session = request.getSession();
            session.invalidate();
            String page = path + "/shop/admin_login.jsp?message=logout";
            response.sendRedirect(page);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
