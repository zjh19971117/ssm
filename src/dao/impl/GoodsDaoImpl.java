package dao.impl;

import dao.GoodsDao;
import entity.Catalog;
import entity.Goods;
import entity.Users;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DBtools;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    private QueryRunner qr = new QueryRunner(DBtools.getDataSource());

    @Override
    public List<Goods> selectAll(int page, int rowsPerPage) {
        String sql = "select * from goods limit ?,?";
        try {
            return qr.query(sql, new BeanListHandler<Goods>(Goods.class), (page - 1) * rowsPerPage, rowsPerPage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods selectOne(int id) {
        String sql = "select * from goods where gid=?";
        try {
            return qr.query(sql, new BeanHandler<Goods>(Goods.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    @Override
    public int getCount() {
        try {
            Long res = qr.query("select count(*) from goods", new ScalarHandler<>());
            return res.intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Goods> selects(int page, int rowsPerpage, int... id) {
        try {
            StringBuffer sql = new StringBuffer("select * from goods");
            if (id.length == 1) {
                sql.append(" where c1=? limit ?,?");
                return qr.query(sql.toString(), new BeanListHandler<Goods>(Goods.class), id[0], (page - 1) * rowsPerpage, rowsPerpage);

            } else if (id.length == 2) {
                sql.append(" where  c2=? limit ?,?");
                return qr.query(sql.toString(), new BeanListHandler<Goods>(Goods.class), id[1], (page - 1) * rowsPerpage, rowsPerpage);
            }
            sql.append(" limit ?,?");
            return qr.query(sql.toString(), new BeanListHandler<Goods>(Goods.class), (page - 1) * rowsPerpage, rowsPerpage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCounts(String name, int... cid) {
        int id = 0;
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM goods");
        if (name != null || cid.length > 0) {
            sql.append(" where");
            if (name != null) {
                sql.append(" g_name like ?");
            }
            if (cid.length == 1) {
                sql.append(" c1=?");
                id = cid[0];
            } else if (cid.length == 2) {
                sql.append(" c2=?");
                id = cid[1];
            }
        }
        try {
            return ((Long) qr.query(sql.toString(), new ScalarHandler<>(), id)).intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Goods> selectByLike(String search, int page, int rowsPerPage) {
        StringBuffer sql = new StringBuffer("select * from goods");
        if (search != null) {
            sql.append(" where gname like ?");
        }
        sql.append(" limit ?,?");
        try {
            if (search != null)
                return qr.query(sql.toString(), new BeanListHandler<Goods>(Goods.class), "%" + search + "%", (page - 1) * rowsPerPage, rowsPerPage);
            return qr.query(sql.toString(), new BeanListHandler<Goods>(Goods.class), (page - 1) * rowsPerPage, rowsPerPage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCountes(String search) {
        try {
            String sql = "select count(*) from goods";
            Long res = 0L;
            if (search != null) {
                sql += " where gname like ?";
                res = qr.query(sql, new ScalarHandler<>(), "%" + search + "%");
                return res.intValue();
            }
            res = qr.query(sql, new ScalarHandler<>());
            return res.intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateGoods(Goods goods) {
        String sql = "update goods set gname=?,price=?,photo=?,pic1=?,pic2=?,pic3=?,pic4=?,info=? WHERE gid=?";
        try {
            return qr.update(sql, goods.getGname(), goods.getPrice(), goods.getPhoto(), goods.getPic1(), goods.getPic2(), goods.getPic3(), goods.getPic4(), goods.getInfo(), goods.getGid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Goods selectGoodsPhoto(int gid) {
        String sql = "select photo from goods where gid=?";
        try {
            return qr.query(sql, new BeanHandler<Goods>(Goods.class), gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods selectGoodsPic1(int gid) {
        String sql = "select pic1 from goods where gid=?";
        try {
            return qr.query(sql, new BeanHandler<Goods>(Goods.class), gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods selectGoodsPic2(int gid) {
        String sql = "select pic2 from goods where gid=?";
        try {
            return qr.query(sql, new BeanHandler<Goods>(Goods.class), gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods selectGoodsPic3(int gid) {
        String sql = "select pic3 from goods where gid=?";
        try {
            return qr.query(sql, new BeanHandler<Goods>(Goods.class), gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods selectGoodsPic4(int gid) {
        String sql = "select pic4 from goods where gid=?";
        try {
            return qr.query(sql, new BeanHandler<Goods>(Goods.class), gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Catalog selectCatlogId(String cname) {
        String sql = "select * from catalog where cname=?";
        try {
            return qr.query(sql,new BeanHandler<Catalog>(Catalog.class),cname);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateC2(int cid, int i) {
        String sql = "update goods set c2=? where gid=?";
        try {
            return qr.update(sql,cid,i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteGoods(int gid) {
        String sql = "delete from goods where gid=?";
        try {
            return qr.update(sql,gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertGoods(Goods goods) {
        String sql = "insert into goods values(NULL,?,?,?,?,?,?,?,?,NULL,NULL)";
        try {
            return qr.update(sql,goods.getGname(),goods.getPrice(),goods.getPhoto(),goods.getPic1(),goods.getPic2(),goods.getPic3(),goods.getPic4(),goods.getInfo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}