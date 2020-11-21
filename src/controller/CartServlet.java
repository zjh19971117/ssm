package controller;

import dao.impl.ShopCart;
import dao.impl.ShopItem;
import entity.Goods;
import entity.Users;
import service.CartService;
import service.GoodsService;
import service.GoodsServiceImpl;
import service.impl.CartServiceImpl;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
    private CartService cartService=new CartServiceImpl();
    private GoodsService goodsService=new GoodsServiceImpl();
    public String del(HttpServletRequest request, HttpServletResponse response){
        System.out.println(123456);
        String gid = request.getParameter("gid");
        int id = Integer.valueOf(gid);
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");
        Object obj2 = session.getAttribute("users");
        if(obj!=null && obj instanceof ShopCart){
            ShopCart shopCart = (ShopCart)obj;
            shopCart.del(id);
            if(obj2!=null && obj2 instanceof Users){
                Users users = (Users)obj2;
                cartService.del(id,users.getUid());
                System.out.println(789);
                int total=shopCart.getTotalPrice();
                return "ajax:{\"total\":"+total+"}";
            }
        }
        return "ajax:{\"error\":\"删除失败\"}";
    }

        public String modify(HttpServletRequest request, HttpServletResponse response){
            String ids=request.getParameter("id");
            int id = Integer.valueOf(ids);
            HttpSession session = request.getSession();
            Object obj=session.getAttribute("cart");
            if(obj!=null && obj instanceof ShopCart){
                ShopCart shopCart = (ShopCart)obj;
                ShopItem shopItem = shopCart.get(id);
                int i = shopItem.getNum();
                if(i > 1){
                    shopItem.setNum(shopItem.getNum()-1);
                }else{
                    shopItem.setNum(1);
                }

             return "ajax:{\"num\":"+shopItem.getNum()+",\"total\":"+shopCart.getTotalPrice()+"}";

            }
            return "ajax:{\"error\":\"nodata\"}";
        }
        public String addCart(HttpServletRequest request, HttpServletResponse response){
            String ids = request.getParameter("id");
            int id = Integer.valueOf(ids);
            HttpSession session = request.getSession();
            Object obj=session.getAttribute("cart");
            if(obj!=null && obj instanceof ShopCart) {
                ShopCart shopCart = (ShopCart) obj;
                ShopItem shopItem = shopCart.get(id);
                shopItem.setNum(shopItem.getNum() + 1);
                return "ajax:{\"num\":"+shopItem.getNum()+",\"total\":"+shopCart.getTotalPrice()+"}";
            }
            return "ajax:{\"error\":\"nodata\"}";
        }
        public String show(HttpServletRequest request, HttpServletResponse response){
            HttpSession session = request.getSession();
            Object obj=session.getAttribute("cart");
            if(obj!=null && obj instanceof ShopCart){
                ShopCart shopCart=(ShopCart)obj;
                Collection<ShopItem> shopItems=shopCart.getValus();
                request.setAttribute("items",shopItems);
                request.setAttribute("total",shopCart.getTotalPrice());
            }
            return "cart";
        }

        public String add(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            int gid=Integer.valueOf(id);
            Goods goods=goodsService.selectById(gid);
            //加入购物车处理
            //int res=cartService.addCart(gid);
            //首先判断session中是否有购物车信息
            //获得session
            HttpSession session= request.getSession(true);
            Object obj=session.getAttribute("cart");
            ShopCart shopCart=new ShopCart();
            if(obj!=null && obj instanceof ShopCart){
                //有购物车信息
                shopCart=(ShopCart)obj;//从session中取出购物车信息，并转换为ShopCart
            }
            int res=shopCart.addCart(goods);
            if(res==1){
                //将购物车信息存入到session中
                session.setAttribute("cart",shopCart);
                return "ajax:{\"code\":1}";
            }
            return "ajax:{\"code\":0}";//Msg---code/info/data....  JSON.toJsonString(msg对象)转为json串
        }
    public String deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Object cart = session.getAttribute("cart");
        if(cart != null && cart instanceof ShopCart){
            ShopCart shopCart = (ShopCart)cart;
            shopCart.deleteCart();
        }
        cartService.deleteCart();
     return "servlet:CartServlet?action=show";
    }
}
