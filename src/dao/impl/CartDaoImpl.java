package dao.impl;

import dao.CartDao;
import entity.Cart;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DBtools;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private QueryRunner qr = new QueryRunner(DBtools.getDataSource());
    @Override
    public int add(Cart cart) {
        try {
            return qr.update("insert into cart values(null,?,?,?,?)",cart.getGid(),cart.getNum(),cart.getPrice(),cart.getUid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Cart> selectByUid(int uid) {
        try {
            return qr.query("select * from cart where uid=?",new BeanListHandler<Cart>(Cart.class),uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCount(int uid, int gid) {
        try {
            Long res=qr.query("select count(*) from cart where gid=? and uid=?",new ScalarHandler<>(),gid,uid);
            return res.intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Cart cart) {
        try {
            return qr.update("update cart set num=?,price=? where uid=? and gid=?",cart.getNum(),cart.getPrice(),cart.getUid(),cart.getGid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int del(int gid, int uid) {
        try {
            return qr.update("delete from cart where uid=? and gid=?", uid, gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCart() {
        String sql="TRUNCATE TABLE cart";
        try {
            return qr.update(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
