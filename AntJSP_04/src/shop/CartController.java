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
        String userid=(String)session.getAttribute("userid");
        if(url.indexOf("insert.do")!=-1){
            if(userid==null){
                response.sendRedirect(path+"/shop/login.jsp");
            }else{
                CartDTO dto = new CartDTO();
                dto.setUserid(userid);
                dto.setProduct_code(Integer.parseInt(request.getParameter("product_code")));
                dto.setAmount(Integer.parseInt(request.getParameter("amount")));
                dao.insert_cart(dto);
                response.sendRedirect(path+"/cart_servlet/list.do");
            }
        }else if(url.indexOf("list.do")!=-1){
            int sum_money=dao.sum_money(userid);
            int fee=sum_money>=50000 ? 0 : 2500;
            int sum=sum_money+fee;
            Map<String,Object> map=new HashMap<>();
            map.put("sum_money",sum_money);
            map.put("fee",fee);
            map.put("sum",sum);
            request.setAttribute("map",map);
            List<CartDTO> items = dao.list_cart(userid);
            request.setAttribute("list",items);
            String page="/shop/cart_list.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request,response);
        }else if(url.)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
