package zhr.filetr;

import javax.servlet.*;
import java.io.IOException;

public class cahracter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                            request.setCharacterEncoding("GBK");
                            response.setCharacterEncoding("GBK");
                            chain.doFilter(request,response);
    }

    public void destroy() {

    }
}
