package controller;

import entity.Catalog;
import entity.PageBean;
import entity.Users;
import factory.Factory;
import service.CatalogService;
import service.UserService;
import service.impl.CatalogServiceImpl;
import service.impl.UserServiceImpl;
import util.Sys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private int rowsPerPage = 5;
    private UserService userService = Factory.getImpl(UserServiceImpl.class);
    private CatalogService catalogService = new CatalogServiceImpl();

    protected String getCK(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Cookie[] cks = req.getCookies();//获得cookie
        for (Cookie ck : cks
        ) {
            String decode = URLDecoder.decode(ck.getName(), "utf-8");
            if (name.equals(decode)) {
                String decode1 = URLDecoder.decode(ck.getValue(), "utf-8");
                return "ajax:{\"pass\":\"" + decode1 + "\"}";
            }
        }
        return "ajax:{\"pass\":\"\"}";
    }

    //    public String login(HttpServletRequest request, HttpServletResponse reponse) {
//        String name = request.getParameter("username");
//        String pass = request.getParameter("password");
//        String code = request.getParameter("usrcode");
//        String nologin = request.getParameter("nologin");
//        System.out.println(1123);
//        //登录成功
//        Users users=userService.select(-1,name,pass);
//        if(users!=null && users instanceof  Users) {
//            HttpSession session=request.getSession();
//            Object obj=session.getAttribute(Sys.CODE);
//            if(obj!=null && obj instanceof  String){
//                String checkcod=(String)obj;
//                if(checkcod.equals(code)){
//                    //在session中存储，存在服务器端
//                    session.setAttribute("users",users);
//                    System.out.println(users);
//                    //在cookie中存储，存在客户端
//                    if("free".equals(nologin)){//nologin.equals("free")
//                        Cookie ck=new Cookie(name,pass);//Calendar
//                        ck.setMaxAge(14*24*60*60);
//                        reponse.addCookie(ck);
//                    }
//                    //response.sendRedirect("load");
//                    return "servlet:LoadServlet";
//                }else{
//                    request.setAttribute("err","验证码填写有误！");
//                    return "login";//携带错误信息，返回登录页面
//                }
//            }
//        }
//        request.setAttribute("err","信息填写误！");
//        return "login";//携带错误信息，返回登录页面
//    }
    protected String selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search != null && search.length() > 0) {
            request.setAttribute("search", search);
        } else {
            search = null;
        }
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;//如果不传参，则默认第一页
        List<Users> list = userService.selectAll(search, page, rowsPerPage);
        System.out.println(list);
        request.setAttribute("list", list);
        PageBean pb = new PageBean();
        pb.setPage(page);
        pb.setRowsNum(userService.getCount(search));
        pb.setRowsPerPage(this.rowsPerPage);
        request.setAttribute("pb", pb);
        return "users";
    }

    protected String selectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        int id = ids != null ? Integer.valueOf(ids) : 1;
        Users users = userService.selectOne(id);
        request.setAttribute("users", users);
        return "usersone";
    }

    protected String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        String usrname = request.getParameter("uname");
        String gender = request.getParameter("ugender");
        String email = request.getParameter("uemail");
        int id = ids != null ? Integer.valueOf(ids) : 1;
        Users users = new Users(id, usrname, email, gender);
        userService.update(users);
        return this.selectAll(request, response);

    }

    protected String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        int id = ids != null ? Integer.valueOf(ids) : 1;
        userService.delete(id);
        return "servlet:UserServlet?action=selectAll";

    }

    protected String addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        String usrname = request.getParameter("uname");
        String gender = request.getParameter("ugender");
        String pass = request.getParameter("upass");
        String email = request.getParameter("uemail");
        int id = ids != null ? Integer.valueOf(ids) : 1;
        Users u = new Users(id, usrname, pass, email, gender);
        userService.addUser(u);
        return "servlet:UserServlet?action=selectAll";
    }

    protected String select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("usrcode");
        String nologin = request.getParameter("nologin");
        Users users = userService.select(-1, username, password);
        if (users != null && users instanceof Users) {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(Sys.CODE);
            if (obj != null && obj instanceof String) {
                String checkcod = (String) obj;
                if (checkcod.equals(code)) {
                    //在session中存储，存在服务器端
                    session.setAttribute("users", users);
                    //在cookie中存储，存在客户端
                    if ("free".equals(nologin)) {//nologin.equals("free")
                        Cookie ck = new Cookie(URLEncoder.encode(username, "utf-8"), URLEncoder.encode(password, "utf-8"));//Calendar
                        ck.setMaxAge(14 * 24 * 60 * 60);
                        response.addCookie(ck);
                    }
                    //response.sendRedirect("load");
                    return "servlet:LoadServlet";
                } else {
                    request.setAttribute("err", "验证码填写有误！");
                    return "login";//携带错误信息，返回登录页面
                }
            }
        }
        request.setAttribute("err", "信息填写误！");
        return "login";//携带错误信息，返回登录页面
    }

    protected String catalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Catalog> clist = catalogService.catalog(null);
        request.setAttribute("clist", clist);
        return "index";
    }

    public String check(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        System.out.println(name);
        Users u = userService.check1(name);
        System.out.println(u);
        if (u != null) {
            System.out.println(123);
            return "ajax:sbdonxi";

        } else {
            return "ajax:ok";
        }
    }

    public String logout(HttpServletRequest request, HttpServletResponse reponse) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "servlet:LoadServlet";
    }

}