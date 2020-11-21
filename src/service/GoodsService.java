package service;

import entity.Catalog;
import entity.Goods;
import entity.Users;

import java.util.List;

public interface GoodsService {
    List<Goods> selectAll(int page, int rowsPerpage);

    Goods selectById(int id);

    int getCount();

    List<Goods> selectByPage(int page, int rowsPerpage, int... id);

    int getCounts(String name, int... cid);

    List<Goods> selectAllByLike(String search, int page, int rowsPerPage);

    int getCountes(String search);

    int updateGoods(Goods goods);

    Goods selectGoodsPhoto(int gid);

    Goods selectGoodsPic1(int gid);

    Goods selectGoodsPic2(int gid);

    Goods selectGoodsPic3(int gid);

    Goods selectGoodsPic4(int gid);

    Catalog selectCatlogId(String cname);

    int updateC2(int cid, int i);

    int deleteGoods(int gid);

    int insertGoods(Goods goods);
}
