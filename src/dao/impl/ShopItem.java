package dao.impl;

import entity.Goods;

public class ShopItem {
    private Goods goods;
    private int num=1;//默认数量是1

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
