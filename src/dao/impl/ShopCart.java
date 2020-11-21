package dao.impl;

import entity.Goods;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShopCart {
    private Map<Integer,ShopItem> cart=new HashMap<>();//模拟一个购物车
    public void del(int gid){
        ShopItem item=cart.remove(gid);
        System.out.println("shopCart...."+item);
    }
    //统计总价
    public int getTotalPrice(){
        int total = 0;
        for (ShopItem shopItem : cart.values()){
            total += shopItem.getNum() * shopItem.getGoods().getPrice();
        }
        return total;
    }
    //根据键值获得shopItem
    public ShopItem get(Integer gid){
        return cart.get(gid);
    }
    public Collection<ShopItem> getValus() {
        return cart.values();
    }
    public void put(Integer gid,ShopItem shopItem){
        cart.put(gid,shopItem);
    }
    //加入购物车
    public int addCart(Goods goods){//2-python;3-h5;1-java(2)
        ShopItem shopItem=new ShopItem();
        if(cart.containsKey(goods.getGid())) { //判断map的键值是否存在
            //如果存在，从购物车中取出值shopItem，并修改数量
            shopItem=cart.get(goods.getGid());
            shopItem.setNum(shopItem.getNum()+1);
        }
        shopItem.setGoods(goods);
        cart.put(goods.getGid(),shopItem);
        return 1;
    }


    public void deleteCart() {
        cart.clear();
    }
}


