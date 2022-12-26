package common;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private String charset = "utf-8";
    public void init(FilterConfig config) throws ServletException {
        System.out.println("필터가 초기화되었습니다.");
    }

    public void destroy() {
        System.out.println("필터가 종료되었습니다.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        request.setCharacterEncoding(charset);
        System.out.println("필터가 실행되었습니다.");
        chain.doFilter(request, response);

    }
}
