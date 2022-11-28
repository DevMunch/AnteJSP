package page;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/page_servlet/*")
public class PageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();    // 요청한 주소
        EmpDAO dao = new EmpDAO();
        if(url.indexOf("list.do")!=-1){
            int count=dao.count();  // 레코드 개수
            int curPage=1;  // 기본 페이지
            if(request.getParameter("cur_page")!=null){ // 만일 클릭한 페이지번호가 있다면 그 번호를 준다.
                curPage=Integer.parseInt(request.getParameter("cur_page"));
            }
            PageUtil util=new PageUtil(count, curPage);
            int start=util.getPageBegin();  // 레코드 시작번호
            int end=util.getPageEnd();      // 레코드 끝번호
            List<EmpDTO> list=dao.list(start,end);  // 페이지의 리스트를 가져온다.
            request.setAttribute("list",list);
            request.setAttribute("page",util);
            String page = "/page/list.jsp"; // 출력 페이지
            RequestDispatcher rd=request.getRequestDispatcher(page);
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
