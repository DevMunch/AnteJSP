package shop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();   // 요청한 주소
        ProductDAO dao = new ProductDAO();
        if(url.indexOf("list.do")!=-1){
            List<ProductDTO> items = dao.listProduct();
            request.setAttribute("list", items); // 저장
            RequestDispatcher rd = request.getRequestDispatcher("/shop/product_list.jsp");
            rd.forward(request,response);   // 출력 페이지로 이동
        }else if(url.indexOf("detail.do")!=-1){
            // 상품코드
            int product_code = Integer.parseInt(request.getParameter("product_code"));
            ProductDTO dto = dao.detailProduct(product_code);
            request.setAttribute("dto",dto); // 저장
            RequestDispatcher rd = request.getRequestDispatcher("/shop/product_detail.jsp");
            rd.forward(request,response);   // 출력 페이지로 이동
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
