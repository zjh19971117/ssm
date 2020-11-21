package controller;

import com.alibaba.fastjson.JSON;
import entity.Catalog;
import service.CatalogService;
import service.impl.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CatalogServlet")
public class CatalogServlet extends BaseServlet {
    private CatalogService catalogService = new CatalogServiceImpl();
    protected String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        Integer cid = Integer.valueOf(id);
        List<Catalog> clist= catalogService.load(cid);
        return "ajax:"+ JSON.toJSONString(clist);
    }


}
