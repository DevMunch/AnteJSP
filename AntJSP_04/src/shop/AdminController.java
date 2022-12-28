package shop;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/admin_servlet/*")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String path = request.getContextPath();
        if(url.indexOf("login.do")!=-1){
            AdminDAO dao = new AdminDAO();
            String userid = request.getParameter("userid");
            String passwd = request.getParameter("passwd");
            MemberDTO dto = new MemberDTO();
            dto.setUserid(userid);
            dto.setPasswd(passwd);
            String name = dao.login(dto);
            if(name==null){ // 로그인 실패
                String page = path + "/shop/admin_login.jsp?message=error";
                response.sendRedirect(page); // 로그인 페이지로 이동
            }else{ // 로그인 성공
                HttpSession session = request.getSession(); // 세션 생성
                // 세션변수 저장
                session.setAttribute("admin_userid", dto.getUserid());
                session.setAttribute("userid", userid);
                session.setAttribute("name", name);
                session.setAttribute("result", name + "님 환영합니다.");
                String page = "/shop/admin_result.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request, response);
            }
        }else if(url.indexOf("logout.do")!=-1){
            HttpSession session = request.getSession();
            session.invalidate();
            String page = path + "/shop/admin_login.jsp?message=logout";
            response.sendRedirect(page);
        }else if(url.indexOf("insert_product.do")!=-1){
            /*
            * 개발 디렉토리
            * 배포 디렉토리
            */
            ProductDAO dao = new ProductDAO();
            // 배포 디렉터리에 바로 이미지를 업로드하기 위한 코드
            ServletContext application=request.getSession().getServletContext(); // 서버 전체에 공유하는 객체
            String img_path=application.getRealPath("/images/"); // 실제 서비스되는 경로

            // 파일 첨부 기능이 추가된 객체
            MultipartRequest multi = new MultipartRequest(request,img_path, Constants.MAX_UPLOAD,
                    "utf-8", new DefaultFileRenamePolicy());
            String product_name=multi.getParameter("product_name");
            int price = Integer.parseInt(multi.getParameter("price"));
            String description = multi.getParameter("description");
            String filename=" ";
            try{
                // 첨부파일이 여러개일 수도 있으므로
                Enumeration<String> files = multi.getFileNames();
                while(files.hasMoreElements()){
                    String file1=(String)files.nextElement();
                    filename = multi.getFilesystemName(file1); // 첨부파일 이름
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ProductDTO dto = new ProductDTO();
            dto.setProduct_name(product_name);
            dto.setPrice(price);
            dto.setDescription(description);
            // 파일 이름이 널이거나 빈값이라면
            if(filename==null||filename.trim().equals("")){
                filename="-"; // 나중에 파일이 올라갔는지 검사할때 -으로 검사
            }
            dto.setFilename(filename);
            dao.insertProduct(dto); // 디비에 레코드 저장
            // 목록으로 이동
            String page = path+"/product_servlet/list.do";
            response.sendRedirect(page);
        }else if(url.indexOf("edit.do")!=-1){
            ProductDAO dao = new ProductDAO();
            // 상품코드
            int product_code = Integer.parseInt(request.getParameter("product_code"));
            ProductDTO dto = dao.detailProduct(product_code);
            request.setAttribute("dto",dto); // 저장
            RequestDispatcher rd = request.getRequestDispatcher("/shop/product_edit.jsp");
            rd.forward(request,response); //출력 페이지로 이동
        }else if(url.indexOf("update.do")!=-1){
            ProductDAO dao = new ProductDAO();
            // 배포 디렉터리에 바로 이미지를 업로드하기 위한 코드
            ServletContext application=request.getSession().getServletContext(); // 서버 전체에 공유하는 객체
            String img_path=application.getRealPath("/images/"); // 실제 서비스되는 경로

            // 파일 첨부 기능이 추가된 객체
            MultipartRequest multi = new MultipartRequest(request,img_path, Constants.MAX_UPLOAD,
                    "utf-8", new DefaultFileRenamePolicy());
            String product_name=multi.getParameter("product_name");
            int price = Integer.parseInt(multi.getParameter("price"));
            String description = multi.getParameter("description");
            int product_code=Integer.parseInt(multi.getParameter("product_code")); // 수정을 위한 상품코드(히든)
            String filename=" ";
            try{
                // 첨부파일이 여러개일 수도 있으므로
                Enumeration<String> files = multi.getFileNames();
                while(files.hasMoreElements()){
                    String file1=(String)files.nextElement();
                    filename = multi.getFilesystemName(file1); // 첨부파일 이름
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ProductDTO dto = new ProductDTO();
            dto.setProduct_name(product_name);
            dto.setPrice(price);
            dto.setDescription(description);
            dto.setProduct_code(product_code);
            // 파일 이름이 널이거나 빈값이라면
            if(filename==null||filename.trim().equals("")){ // 즉 새로운 파일추가가 없다면.
                ProductDTO dto2 = dao.detailProduct(product_code); // 기존 정보를 가져온다.
                filename=dto2.getFilename();
                dto.setFilename(filename);
            }else{
                dto.setFilename(filename); // 새로운 첨부파일이 등록되면 바꿔준다.
            }
            dto.setFilename(filename);
            dao.updateProduct(dto); // 디비에 레코드 저장
            // 목록으로 이동
            String page = path+"/product_servlet/list.do";
            response.sendRedirect(page);
        }else if(url.indexOf("delete.do")!=-1){
            ProductDAO dao = new ProductDAO();
            ServletContext application = request.getSession().getServletContext();
            String img_path = application.getRealPath("/images/");
            MultipartRequest multi = new MultipartRequest(request, img_path, Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
            int product_code=Integer.parseInt(multi.getParameter("product_code")); // 상품코드
            dao.deleteProduct(product_code); // 삭제
            String page = path + "/product_servlet/list.do";
            response.sendRedirect(page);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
