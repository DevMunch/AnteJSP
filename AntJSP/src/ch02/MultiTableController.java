package ch02;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/multi_table.do")
public class MultiTableController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num=Integer.parseInt(request.getParameter("num"));
        String result="";
        for(int i=1; i<=9; i++){
            result += num + "x" + i + "=" + num*i + "<br>";
        }
        request.setAttribute("result", result);
        String page="/ch02/multi_result.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
