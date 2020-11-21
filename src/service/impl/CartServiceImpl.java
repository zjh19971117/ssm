package service.impl;

import dao.CartDao;
import dao.GoodsDao;
import dao.impl.CartDaoImpl;
import dao.impl.GoodsDaoImpl;
import dao.impl.ShopCart;
import entity.Cart;
import entity.Goods;
import service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();


    @Override
    public List<Cart> selectByUid(int uid) {
        return cartDao.selectByUid(uid);
    }

    @Override
    public int addCart(Cart cart) {
        return cartDao.add(cart);
    }

    @Override
    public int selectCount(int uid, int gid) {
        return cartDao.selectCount(uid,gid);
    }

    @Override
    public int update(Cart cart) {
        return cartDao.update(cart);
    }

    @Override
    public int del(int uid, int gid) {
        return cartDao.del(uid,gid);
    }

    @Override
    public int deleteCart() {
        int i = cartDao.deleteCart();
        return i;
    }
}
