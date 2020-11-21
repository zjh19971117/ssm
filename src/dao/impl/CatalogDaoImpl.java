package dao.impl;

import dao.CatalogDao;
import entity.Catalog;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.DBtools;

import java.sql.SQLException;
import java.util.List;

public class CatalogDaoImpl implements CatalogDao {
  private QueryRunner qr = new QueryRunner(DBtools.getDataSource());
    @Override
    public List<Catalog> catalog(Integer id) {
        try {
        StringBuffer sql = new StringBuffer("select * from catalog where ");
        if(id == null) {
            sql.append("clevel=1");
            return qr.query(sql.toString(),new BeanListHandler<Catalog>(Catalog.class));
        }
        else{
            sql.append("clevel=2 and cparent=?");
            return qr.query(sql.toString(),new BeanListHandler<Catalog>(Catalog.class),id);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Catalog> selectAll(Integer id) {
        try {
            StringBuffer sql = new StringBuffer("SELECT  cid, cname, clevel,cparent,url,logo FROM catalog WHERE ");
            if (id == null) {
                sql.append("clevel=1");
                return qr.query(sql.toString(), new BeanListHandler<Catalog>(Catalog.class));
            } else {
                sql.append("clevel=2 and cparent=?");
                return qr.query(sql.toString(), new BeanListHandler<Catalog>(Catalog.class), id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(int c1, int gid) {
        String sql="update goods set c1=? where gid=?";
        try {
            return qr.update(sql,c1,gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
