package survey;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SurveyController", value = "/survey_servlet/*")
public class SurveyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath(); // context path
        String url = request.getRequestURI(); // 사용자가 요청한 주소
        SurveyDAO dao = new SurveyDAO();
        if(url.indexOf("input.do")!=-1){
            SurveyDTO dto = dao.view(1); // 1번 문제
            request.setAttribute("dto",dto); // 저장
            // 출력 페이지로 포워드
            RequestDispatcher rd = request.getRequestDispatcher("/survey/input.jsp");
            rd.forward(request,response);
        }else if (url.indexOf("insert.do") != -1) {
            // 문제 번호
            int survey_idx = Integer.parseInt(request.getParameter("survey_idx"));
            // 응답
            int num = Integer.parseInt(request.getParameter("num"));
            AnswerDTO dto = new AnswerDTO();
            dto.setSurvey_idx(survey_idx);
//            dto.setSurvey_idx(survey_idx);
            dto.setNum(num);
            dao.insert(dto); // 저장
            response.sendRedirect(path+ "/survey/success.jsp"); // 완료 페이지
        } else if (url.indexOf("summary.do") != -1) {
            // 문제 번호
            int survey_idx = Integer.parseInt(request.getParameter("survey_idx"));
            // 요약 내용 리스트
            List<SummaryDTO> items = dao.summary(survey_idx);
            request.setAttribute("list", items);
            // 출력 페이지로 포워드
            RequestDispatcher rd = request.getRequestDispatcher("/survey/summary.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
