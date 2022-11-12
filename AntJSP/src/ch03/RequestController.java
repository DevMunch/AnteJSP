package ch03;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ch03/request.do")
public class RequestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 한글 인코딩
        String name=request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String hobby=request.getParameter("hobby");

        // 따로따로 저장해서 보내는 방법.
//        request.setAttribute("name", name);
//        request.setAttribute("age", age);
//        request.setAttribute("gender", gender=="m"?"남":"여");
//        request.setAttribute("hobby", hobby);

        // 맵에 모아서 저장해서 보내는 방법
        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        gender=gender.equals("m") ? "남" : "여";
        map.put("gender", gender);
        map.put("hobby", hobby);
        request.setAttribute("map",map);

        String page = "/ch03/request_result.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
