package dao;

import entity.Cart;

import java.util.List;

public interface CartDao {
    public int add(Cart cart);

    public List<Cart> selectByUid(int uid);

    public int selectCount(int uid, int gid);

    public int update(Cart cart);

    public int del(int gid, int uid);

    int deleteCart();
}
