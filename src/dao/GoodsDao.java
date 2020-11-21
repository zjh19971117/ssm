package dao;

import entity.Catalog;
import entity.Goods;

import java.util.List;

public interface GoodsDao {
    List <Goods> selectAll(int page, int rowsPerPage);
    Goods selectOne(int id);
    int getCount();

    List<Goods> selects(int page, int rowsPerpage, int...id);

    int getCounts(String name, int... cid);

    List<Goods> selectByLike(String search, int page, int rowsPerPage);

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
