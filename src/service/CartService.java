package service;

import entity.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> selectByUid(int uid);

    public int addCart(Cart cart);

    public int selectCount(int uid,int gid);

    public int update(Cart cart);

    public int del(int uid, int gid);

    int deleteCart();
}
