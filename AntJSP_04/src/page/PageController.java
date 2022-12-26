package page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/page_servlet/*")
public class PageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        EmpDAO dao = new EmpDAO();
        if(url.indexOf("list.do")!=-1){
            int count = dao.count(); // 페이지의 총 갯수
            int curPage = 1; // 현재 페이지를 1로설정.
            if(request.getParameter("cur_page")!=null){
                // 전달받은 페이지가 있다면 현재 페이지를 변경.
                curPage = Integer.parseInt(request.getParameter("cur_page"));
            }
            PageUtil page = new PageUtil(count, curPage);
            int start = page.getPageBegin();
            int end = page.getPageEnd();
            List<EmpDTO> list = dao.list(start,end);
            request.setAttribute("list",list);
            request.setAttribute("page",page);
            RequestDispatcher rd = request.getRequestDispatcher("/page/list.jsp");
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
