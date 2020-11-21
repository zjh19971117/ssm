package listener;

import dao.impl.ShopCart;
import dao.impl.ShopItem;
import entity.Cart;
import entity.Users;
import service.CartService;
import service.GoodsService;
import service.GoodsServiceImpl;
import service.impl.CartServiceImpl;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.Collection;
import java.util.List;

@WebListener
public class LoginListenner implements HttpSessionListener, HttpSessionAttributeListener {
    private CartService cartService = new CartServiceImpl();
    private GoodsService goodsService = new GoodsServiceImpl();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        String id = httpSessionEvent.getSession().getId();
        System.out.println("会话创建" + id);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //购物车入库信息
        HttpSession session = httpSessionEvent.getSession();
        Object userObj = session.getAttribute("users");
        if (userObj != null && userObj instanceof Users) {
            Users users = (Users) userObj;
            int uid = users.getUid();
            Object obj = session.getAttribute("cart");
            if (obj != null && obj instanceof ShopCart) {
                ShopCart shopCart = (ShopCart) obj;
                Collection<ShopItem> shopItems = shopCart.getValus();
                //将shopItems转换为cart--曾/该
                for (ShopItem item : shopItems) {
                    int gid = item.getGoods().getGid();
                    Cart cart = new Cart(null, gid, uid, item.getNum(), item.getGoods().getPrice());
                    if (cartService.selectCount(uid, gid) == 0)
                        cartService.addCart(cart);
                    else
                        cartService.update(cart);
                }
            }
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        //setAttribute新增
        HttpSession session = httpSessionBindingEvent.getSession();
        String name = httpSessionBindingEvent.getName();
        Object obj = session.getAttribute("users");
        if (name.equals("users") && obj != null && obj instanceof Users) {
            Users users = (Users) obj;
            //从数据库中取出Cart,装为ShopCart,并存入session
            List<Cart> list = cartService.selectByUid(users.getUid());
            ShopCart shopCart = new ShopCart();
            //cart.put(cart.getGid,shopItem(cart.getNum,goodService.selectOne(cart.getGid())))
            for (Cart cart : list) {
                ShopItem item = new ShopItem();
                item.setNum(cart.getNum());
                item.setGoods(goodsService.selectById(cart.getGid()));
                shopCart.put(cart.getGid(), item);
            }
            //准备好的shopCart存放入session中,会话期间可以共享
            session.setAttribute("cart", shopCart);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
//removeAttribute
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
//setAttribute
    }
}
