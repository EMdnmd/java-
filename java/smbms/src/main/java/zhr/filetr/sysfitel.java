package zhr.filetr;

import zhr.util.constansts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sysfitel  implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
//       过滤器 从session 中获取用户
        Object user = request1.getSession().getAttribute(constansts.USER_SESSION);
        if (user==null){
//            已经被移除或者注销 或者未登录
              response1.sendRedirect("/error.jsp");
        }else{
                chain.doFilter(request,response);
        }

    }

    public void destroy() {

    }
}
