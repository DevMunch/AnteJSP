package common;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// 필터: 선처리 클래스, 필터경유 => list.do 요청
@WebFilter("/*") // 모든 url pattern
public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}
