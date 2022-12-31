package shop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart_servlet/*")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String path= request.getContextPath();
        CartDAO dao = new CartDAO();
        HttpSession session = request.getSession();
        String userid=(String)session.getAttribute("userid"); // 세션체크용 변수
        if(url.indexOf("insert.do")!=-1){ // 장바구니 추가
            if(userid==null){ // 로그아웃 상태
                response.sendRedirect(path+"/shop/login.jsp"); // 로그인 페이지로
            }else{ // 로그인 상태
                CartDTO dto = new CartDTO();
                dto.setUserid(userid); // 누가
                dto.setProduct_code(Integer.parseInt(request.getParameter("product_code"))); // 무슨상품을
                dto.setAmount(Integer.parseInt(request.getParameter("amount"))); // 몇개
                dao.insert_cart(dto); // 저장
                response.sendRedirect(path+"/cart_servlet/list.do"); // 목록으로 이동
            }
        }else if(url.indexOf("list.do")!=-1){ // 장바구니 목록
            int sum_money=dao.sum_money(userid); // 총금액
            int fee=sum_money>=50000 ? 0 : 2500; // 배송료 계산
            int sum=sum_money+fee; // 최종 결제 금액 ( 총금액 + 배송료 )
            Map<String,Object> map=new HashMap<>();
            // 해쉬맵에 저장
            map.put("sum_money",sum_money);
            map.put("fee",fee);
            map.put("sum",sum);
            request.setAttribute("map",map);
            List<CartDTO> items = dao.list_cart(userid); // 장바구니 세부 리스트
            request.setAttribute("list",items);
            String page="/shop/cart_list.jsp"; // 출력 페이지
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request,response);
        }else if(url.indexOf("delete.do")!=-1){ // 장바구니 개별 삭제
            dao.delete_cart(Integer.parseInt(request.getParameter("cart_id")));
            response.sendRedirect(path+"/cart_servlet/list.do");
        }else if(url.indexOf("delete_all.do")!=-1){ // 장바구니 비우기
            dao.clear_cart(userid);
            response.sendRedirect(path+"/cart_servlet/list.do");
        }else if(url.indexOf("update.do")!=-1){ // 장바구니 목록 수정
            String[] product_code=request.getParameterValues("product_code");
            String[] amount=request.getParameterValues("amount");
            String[] cart_id=request.getParameterValues("cart_id");
            for(int i=0; i<product_code.length; i++){
                CartDTO dto = new CartDTO();
                dto.setUserid(userid);
                dto.setCart_id(Integer.parseInt(cart_id[i]));
                dto.setAmount(Integer.parseInt(amount[i]));
                dao.update_cart(dto);
            }
            response.sendRedirect(path+"/cart_servlet/list.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
