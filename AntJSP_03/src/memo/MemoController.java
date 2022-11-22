package memo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemoDAO dao = new MemoDAO();
        String url = request.getRequestURL().toString();
        if(url.indexOf("list.do")!=-1){
            String search_option = request.getParameter("search_option");
            if(search_option==null||search_option.equals("")){
                search_option="writer"; // 기본 검색 옵션
            }
            String search = request.getParameter("search"); // 검색 키워드, 없으면 빈값
            if(search==null){
                search="";
            }
            List<Map<String,Object>> list=dao.list(search_option, search);
            // 데이터 저장
            request.setAttribute("list", list);
            request.setAttribute("search_option", search_option);
            request.setAttribute("search", search);
            // 출력 페이지
            String page = "/memo/list.jsp";
            // 포워딩
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }else if(url.indexOf("insert.do")!=-1) {
            String writer = request.getParameter("writer");
            String memo = request.getParameter("memo");
            Map<String, Object> map = new HashMap<>();
            map.put("writer", writer);
            map.put("memo", memo);
            dao.insert(map);
        }else if(url.indexOf("view.do")!=-1){
            int idx=Integer.parseInt(request.getParameter("idx"));
            Map<String,Object>map=dao.view(idx);
            request.setAttribute("map",map);
            String page="/memo/view.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request,response);
        }else if(url.indexOf("update.do")!=-1){
            int idx=Integer.parseInt(request.getParameter("idx"));
            String writer=request.getParameter("writer");
            String memo=request.getParameter("memo");
            Map<String,Object> map=new HashMap<>();
            map.put("idx",idx);
            map.put("writer",writer);
            map.put("memo",memo);
            dao.update(map);
            response.sendRedirect("/memo/memo.jsp");
        }else if(url.indexOf("del.do")!=-1){
            int idx=Integer.parseInt(request.getParameter("idx"));
            dao.delete(idx);
            response.sendRedirect("/memo/memo.jsp");
        } else if(url.indexOf("delete_all.do")!=-1){
            String[] idx=request.getParameterValues("idx");
            if(idx!=null){
                for(int i=0; i<idx.length; i++){
                    dao.delete(Integer.parseInt(idx[i]));
                }
            }
            response.sendRedirect(request.getContextPath()+"/memo/memo.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
