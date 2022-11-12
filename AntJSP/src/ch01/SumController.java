package ch01;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch01/sum.do")
public class SumController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int number=Integer.parseInt(request.getParameter("number"));
        int sum=0;
        for(int i=1; i<=number; i++){
            sum+=i;
        }
        request.setAttribute("sum",sum);
        RequestDispatcher rd = request.getRequestDispatcher("/ch02/sum_result.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
