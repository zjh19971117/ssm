package filter;

import entity.Users;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/CartServlet")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
     HttpServletRequest request =(HttpServletRequest) req;
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("users");
        HttpServletResponse response=(HttpServletResponse)resp;
        if(obj!=null && obj instanceof Users){
            chain.doFilter(req, resp);
        }else{
            String xhr = request.getHeader("xhr2");
            if(xhr!=null) {
                //未登录：login.jsp
                PrintWriter pw = resp.getWriter();
                pw.write("{\"code\":-1}");
                pw.flush();
            }else{
                response.sendRedirect("login.jsp");
            }
        }

    }
    public void init(FilterConfig config) throws ServletException {

    }

}
