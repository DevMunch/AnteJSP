package session;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_servlet/*")
public class SessionController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        MemberDAO dao = new MemberDAO();
        if (url.indexOf("login.do") != -1) {
            String userid = request.getParameter("userid");
            String passwd = request.getParameter("passwd");
            MemberDTO dto = new MemberDTO();
            dto.setUserid(userid);
            dto.setPasswd(passwd);
            String result = dao.login(dto);
            String page = "/session/login.jsp";

            if (result != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userid", userid);
                session.setAttribute("message", result);
                page = "/session/main.jsp";
            }
            response.sendRedirect(request.getContextPath() + page);
        } else if (url.indexOf("logout.do") != -1) {
            HttpSession session = request.getSession(); session.invalidate();
            String page = request.getContextPath() + "/session/login.jsp?message=logout";
            response.sendRedirect(page);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}