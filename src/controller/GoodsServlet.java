package controller;

import com.alibaba.fastjson.JSON;
import com.jspsmart.upload.*;
import com.jspsmart.upload.File;
import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import entity.Catalog;
import entity.Goods;
import entity.PageBean;
import entity.Users;
import service.CatalogService;
import service.GoodsService;
import service.GoodsServiceImpl;
import service.impl.CatalogServiceImpl;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.io.*;
import java.util.UUID;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    private GoodsService goodsService = new GoodsServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();
    private ServletConfig config;
    int rowsPerPage = 5;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    protected String selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String ids = req.getParameter("id");//取出地址?后的参数名称
        int id = Integer.parseInt(ids);
        //处理业务
        Goods goods = goodsDao.selectOne(id);
        System.out.println(goods);
        //响应结果:存、跳
        req.setAttribute("goods", goods);
        return "one";

    }

    protected String selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;//如果不传参，则默认第一页
        List<Goods> list = goodsService.selectAll(page, rowsPerPage);
        System.out.println(list);
        request.setAttribute("glist", list);
        PageBean pb = new PageBean();
        pb.setPage(page);
        pb.setRowsNum(goodsService.getCount());
        pb.setRowsPerPage(this.rowsPerPage);
        request.setAttribute("pb", pb);
        return "index";
    }

    protected String select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String cid1 = request.getParameter("cid1");
        int id = Integer.valueOf(cid);
        int id1 = Integer.valueOf(cid1);
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;
        List<Goods> list = goodsService.selectByPage(page, 10, id, id1);
        PageBean pb = new PageBean();
        pb.setPage(page);
        pb.setRowsNum(goodsService.getCounts(null, id));
        pb.setRowsPerPage(10);
        request.setAttribute("list", list);
        request.setAttribute("pb", pb);
        return "list";
    }

    protected String catalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;
        List<Goods> list = goodsService.selectAll(page, rowsPerPage);
        List<Catalog> clist = catalogService.catalog(null);
        request.setAttribute("glist", list);
        request.setAttribute("clist", clist);
        return "index";
    }

    protected String selectGoodsAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(123456);
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;//如果不传参，则默认第一页
        List<Goods> list = goodsService.selectAll(page, rowsPerPage);
        System.out.println(list);
        request.setAttribute("glist", list);
        PageBean pb = new PageBean();
        pb.setPage(page);
        pb.setRowsNum(goodsService.getCount());
        pb.setRowsPerPage(this.rowsPerPage);
        request.setAttribute("pb", pb);
        List<Catalog> clist = catalogService.load(null);
        System.out.println(clist);
        request.setAttribute("clist", clist);
        return "goods";
    }

    protected String selectGoodsLike(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("key");
        System.out.println(search);
        if (search != null && search.length() > 0) {
            request.setAttribute("search", search);
        } else {
            search = null;
        }
        String pages = request.getParameter("page");
        int page = pages != null ? Integer.valueOf(pages) : 1;//如果不传参，则默认第一页
        List<Goods> list = goodsService.selectAllByLike(search, page, 10);
        request.setAttribute("list", list);
        PageBean pb = new PageBean();
        pb.setPage(page);
        pb.setRowsNum(goodsService.getCountes(search));
        pb.setRowsPerPage(this.rowsPerPage);
        request.setAttribute("pb", pb);
        return "list";
    }

    protected String updateC1(HttpServletRequest req, HttpServletResponse resp) {
        String gids = req.getParameter("gid");
        String c1s = req.getParameter("c1");
        int gid = gids != null ? Integer.valueOf(gids) : 1;
        int c1 = Integer.valueOf(c1s);
        List<Catalog> clist = catalogService.load(c1);
        int i = catalogService.update(c1, gid);
        System.out.println(i);
        return "ajax:" + JSON.toJSONString(clist);
    }

    protected void updateC2(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入updateC2");
        String cname = req.getParameter("name");
        Catalog catalog = goodsService.selectCatlogId(cname);
        String gid = req.getParameter("gid");
        if (gid == null) {
            System.out.println(123456);
        } else {
            int i = Integer.valueOf(gid);
            goodsService.updateC2(catalog.getCid(), i);
        }


    }

    protected String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        System.out.println("进入load");
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";

//        String gname=req.getParameter("gname");
//        String price=req.getParameter("price");
        java.io.File fs = new java.io.File(req.getServletContext().getRealPath("/") + "\\upload\\");
        if (!fs.exists()) {
            fs.mkdirs();
        }
        SmartUpload su = new SmartUpload();
        try {
            su.initialize(this.config, req, resp);
            su.upload();
            Request request1 = su.getRequest();
            String gid = request1.getParameter("gid");
            if(gid != null && gid.length() > 0){
                System.out.println("gid="+gid);
            String gname = request1.getParameter("gname");
            String price = request1.getParameter("price");
            String info = request1.getParameter("info");
            Files files = su.getFiles();
            Goods goods = new Goods();
            File file1 = files.getFile(0);
            if (file1.getSize() == 0) {
                Goods goods1 = goodsService.selectGoodsPhoto(Integer.valueOf(gid));
                goods.setPhoto(goods1.getPhoto());
            } else {
                s1 = UUID.randomUUID().toString() + "." + file1.getFileExt();
                file1.saveAs(fs + java.io.File.separator + s1);
                goods.setPhoto("\\upload\\" + s1);
            }
            File file2 = files.getFile(1);
            if (file2.getSize() == 0) {
                Goods goods1 = goodsService.selectGoodsPic1(Integer.valueOf(gid));
                goods.setPic1(goods1.getPic1());
            } else {
                s2 = UUID.randomUUID().toString() + "." + file2.getFileExt();
                file2.saveAs(fs + java.io.File.separator + s2);
                goods.setPic1("\\upload\\" + s2);
            }
            File file3 = files.getFile(2);
            if (file3.getSize() == 0) {
                Goods goods1 = goodsService.selectGoodsPic2(Integer.valueOf(gid));
                goods.setPic2(goods1.getPic2());
            } else {
                s3 = UUID.randomUUID().toString() + "." + file3.getFileExt();
                file3.saveAs(fs + java.io.File.separator + s3);
                goods.setPic2("\\upload\\" + s3);
            }
            File file4 = files.getFile(3);
            if (file4.getSize() == 0) {
                Goods goods1 = goodsService.selectGoodsPic3(Integer.valueOf(gid));
                goods.setPic3(goods1.getPic3());
            } else {
                s4 = UUID.randomUUID().toString() + "." + file4.getFileExt();
                file4.saveAs(fs + java.io.File.separator + s4);
                goods.setPic3("\\upload\\" + s4);
            }
            File file5 = files.getFile(4);
            if (file5.getSize() == 0) {
                Goods goods1 = goodsService.selectGoodsPic4(Integer.valueOf(gid));
                goods.setPic4(goods1.getPic4());
            } else {
                s5 = UUID.randomUUID().toString() + "." + file5.getFileExt();
                file5.saveAs(fs + java.io.File.separator + s5);
                goods.setPic4("\\upload\\" + s5);
            }
            goods.setGid(Integer.valueOf(gid));
            goods.setGname(gname);
            goods.setPrice(Integer.valueOf(price));
            goods.setInfo(info);
            int i = goodsService.updateGoods(goods);
            return "servlet:GoodsServlet?action=selectGoodsAll";
        }else{
                System.out.println("进入添加");
                String gid1 = request1.getParameter("gid");
                String gname = request1.getParameter("gname");
                String price = request1.getParameter("price");
                String info = request1.getParameter("info");
                Files files = su.getFiles();
                Goods goods = new Goods();

                File file1 = files.getFile(0);
                s1 = UUID.randomUUID().toString() + "." + file1.getFileExt();
                file1.saveAs(fs + java.io.File.separator + s1);
                goods.setPhoto("\\upload\\" + s1);

                File file2 = files.getFile(1);
                s2 = UUID.randomUUID().toString() + "." + file2.getFileExt();
                file2.saveAs(fs + java.io.File.separator + s2);
                goods.setPic1("\\upload\\" + s2);

                File file3 = files.getFile(2);
                s3 = UUID.randomUUID().toString() + "." + file3.getFileExt();
                file3.saveAs(fs + java.io.File.separator + s3);
                goods.setPic2("\\upload\\" + s3);

                File file4 = files.getFile(3);
                s4 = UUID.randomUUID().toString() + "." + file4.getFileExt();
                file4.saveAs(fs + java.io.File.separator + s4);
                goods.setPic3("\\upload\\" + s4);

                File file5 = files.getFile(4);
                s5 = UUID.randomUUID().toString() + "." + file5.getFileExt();
                file4.saveAs(fs + java.io.File.separator + s5);

                goods.setPic4("\\upload\\" + s5);
                goods.setGname(gname);
                goods.setPrice(Integer.valueOf(price));
                goods.setInfo(info);
                goodsService.insertGoods(goods);
                return "servlet:GoodsServlet?action=selectGoodsAll";
            }
        } catch (SmartUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String select1(HttpServletRequest req,HttpServletResponse resp){
        String gids = req.getParameter("gid");
        int id = Integer.valueOf(gids);
        Goods goods = goodsDao.selectOne(id);
        return "ajax:" + JSON.toJSONString(goods);
    }
    public String delete(HttpServletRequest req,HttpServletResponse resp){
        System.out.println("进入delete");
        String gids = req.getParameter("gid");
        int gid = Integer.valueOf(gids);
        int res = goodsService.deleteGoods(gid);
        System.out.println(res);
        return "ajax:" + JSON.toJSONString(res);
    }

}
