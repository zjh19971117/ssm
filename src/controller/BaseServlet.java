package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action="load";
        }
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            Object obj = method.invoke(this,req,resp);
            if(obj != null && obj instanceof String) {
                String page = (String) obj;
                if(page.startsWith("ajax")){
                    PrintWriter writer = resp.getWriter();
                    writer.write(page.substring(5));
                    writer.flush();
                    return;
                }
                if (page.startsWith("redirect:")) {
                    resp.sendRedirect(page.split(":")[1] + ".jsp");
                } else if (page.startsWith("servlet:")) {
                    resp.sendRedirect(page.split(":")[1]);
                } else {
                    req.getRequestDispatcher(page + ".jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
