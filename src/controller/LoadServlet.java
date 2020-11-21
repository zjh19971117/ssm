package controller;

import entity.Catalog;
import entity.Goods;
import service.CatalogService;
import service.GoodsService;
import service.GoodsServiceImpl;
import service.impl.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/LoadServlet")
public class LoadServlet extends BaseServlet{
    private  int rowsPerPage=10;
    private GoodsService goodsService=new GoodsServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();
    protected String load1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Goods> list=goodsService.selectAll(1,this.rowsPerPage);
        request.setAttribute("list",list);
        return "index";
    }
    protected String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;
        List<Goods> list = goodsService.selectAll(page,rowsPerPage);
        List<Catalog> clist = catalogService.catalog(null);
        request.setAttribute("glist", list);
        request.setAttribute("clist",clist);
        return "index";
    }

}
