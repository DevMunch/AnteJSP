package common;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("/*") // 모든 url pattern 에 적용
public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";

    public void init(FilterConfig config) throws ServletException {
//        encoding = config.getInitParameter("BrowserEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 이전 실행, 선처리
        request.setCharacterEncoding(encoding);
        // 메인 실행
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
