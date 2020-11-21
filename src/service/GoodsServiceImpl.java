package service;

import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import entity.Catalog;
import entity.Goods;

import java.util.List;

public class GoodsServiceImpl implements GoodsService{
    private GoodsDao goodsDao=new GoodsDaoImpl();
    @Override
    public List<Goods> selectAll(int page, int rowsPerpage) {
        return goodsDao.selectAll(page,rowsPerpage);
    }

    @Override
    public Goods selectById(int id) {
        return goodsDao.selectOne(id);
    }

    @Override
    public int getCount() {
        return goodsDao.getCount();
    }

    @Override
    public List<Goods> selectByPage(int page, int rowsPerpage, int... id) {
        return goodsDao.selects(page,rowsPerpage,id);
    }

    @Override
    public int getCounts(String name, int... cid) {
        return goodsDao.getCounts(name,cid);
    }

    @Override
    public List<Goods>  selectAllByLike(String search, int page, int rowsPerPage) {
        return goodsDao.selectByLike(search,page,rowsPerPage);
    }

    @Override
    public int getCountes(String search) {
        return goodsDao.getCountes(search);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public Goods selectGoodsPhoto(int gid) {
        return goodsDao.selectGoodsPhoto(gid);
    }

    @Override
    public Goods selectGoodsPic1(int gid) {
        return goodsDao.selectGoodsPic1(gid);
    }

    @Override
    public Goods selectGoodsPic2(int gid) {
        return goodsDao.selectGoodsPic2(gid);
    }

    @Override
    public Goods selectGoodsPic3(int gid) {
        return goodsDao.selectGoodsPic3(gid);
    }

    @Override
    public Goods selectGoodsPic4(int gid) {
        return goodsDao.selectGoodsPic4(gid);
    }

    @Override
    public Catalog selectCatlogId(String cname) {
        return goodsDao.selectCatlogId(cname);
    }

    @Override
    public int updateC2(int cid, int i) {
        return goodsDao.updateC2(cid,i);
    }

    @Override
    public int deleteGoods(int gid) {
        return goodsDao.deleteGoods(gid);
    }

    @Override
    public int insertGoods(Goods goods) {
        return goodsDao.insertGoods(goods);
    }

}
